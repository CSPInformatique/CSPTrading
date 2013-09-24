package com.cspinformatique.csptrading.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.QuoteStats;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.sql.QuoteStatsRepository;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.QuoteStatsService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.util.MarketUtil;

@Service
public class QuoteStatsServiceImpl implements QuoteStatsService {
	@Autowired private QuoteService quoteService;
	@Autowired private QuoteStatsRepository quoteStatsRepository;
	@Autowired private StockService stockService;
	
	@Autowired private NumberFormat numberFormat;
	
	@Override
	public void save(QuoteStats quoteStats) {
		this.quoteStatsRepository.save(quoteStats);
	}
	
	@Override
	public void generateQuoteStats(Stock stock, Date date){
		try{
			double lowAverage =	this.numberFormat.parse(this.numberFormat.format(
									this.stockService.getAverageLowQuote(stock, MarketUtil.getOpenedDatesSinceDays(5))
								)).doubleValue();
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			double last = quoteService.findLastQuote(stock.getId()).getLast();
			
			this.save(
				new QuoteStats(
					0, 
					stock, 
					calendar.getTime(), 
					last,
					lowAverage, 
					this.numberFormat.parse(this.numberFormat.format(
						(last * 100 / lowAverage) - 100
					)).doubleValue()
				)
			);
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}

	@Override
	public QuoteStats getQuoteStats(long stockId, Date date) {
		return this.quoteStatsRepository.findByStockIdAndDate(stockId, date);
	}
}
