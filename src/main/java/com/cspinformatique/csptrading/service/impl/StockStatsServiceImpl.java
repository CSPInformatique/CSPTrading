package com.cspinformatique.csptrading.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.StockStats;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.sql.StockStatsRepository;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockStatsService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.util.MarketUtil;

@Service
public class StockStatsServiceImpl implements StockStatsService {
	private static final Logger logger = LoggerFactory.getLogger(StockStatsServiceImpl.class);
	
	@Autowired private QuoteService quoteService;
	@Autowired private StockStatsRepository stockStatsRepository;
	@Autowired private StockService stockService;
	
	@Autowired private NumberFormat numberFormat;
	
	@Override
	public void save(StockStats stockStats) {
		this.stockStatsRepository.save(stockStats);
	}

	public void generateStockStats(Stock stock, Date date){
		this.generateStockStats(stock, Arrays.asList(new Date[]{date}));
	}
	
	@Override
	public void generateStockStats(Stock stock, List<Date> dates){
		try{			
			for(Date date : dates){
				List<Date> openedDays = MarketUtil.getOpenedDatesSinceDays(5, date);
				List<Quote> quotes = this.quoteService.loadQuotesFromProvider(stock, openedDays.get(openedDays.size() - 1), date);
				
				double lowAverage =	this.numberFormat.parse(this.numberFormat.format(
										this.stockService.getAverageLowQuote(stock, quotes)
									)).doubleValue();
				
				if(Double.isNaN(lowAverage)){
					lowAverage = 0;
				}
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				
				Quote lastQuote =	quotes.get(quotes.size() - 1);
				
				if(lastQuote != null){
					double last =	lastQuote.getClose();
					
					double lowVariableAverage =	this.numberFormat.parse(this.numberFormat.format(
													(last * 100 / lowAverage) - 100
												)).doubleValue();
					
					if(Double.isInfinite(lowVariableAverage)){
						lowVariableAverage = 0;
					}
					
					logger.info("Saving stock stats for " + stock.getSymbol() + " on " + date);
					
					this.save(
						new StockStats(
							stock, 
							calendar.getTime(), 
							last,
							lowAverage, 
							lowVariableAverage
						)
					);
				}else{
					logger.warn("No quote found for " + stock.getSymbol() + " on " + date);
				}
			}
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}
	
	@Override
	public List<StockStats> getStocksStats(Date date) {
		logger.debug("Loading stocks stats on " + date);
		
		List<StockStats> stockStats = this.stockStatsRepository.findByDate(date);
		
		logger.debug("Stocks stats loading completed.");
		
		return stockStats;
	}

	@Override
	public StockStats getStockStats(long stockId, Date date) {
		return this.stockStatsRepository.findByStockIdAndDate(stockId, date);
	}
}
