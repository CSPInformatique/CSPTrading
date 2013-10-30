package com.cspinformatique.csptrading.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.activetick.ActiveTickConnector;
import com.cspinformatique.csptrading.activetick.QuoteHistoryRequestor;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.mongo.QuoteRepository;
import com.cspinformatique.csptrading.service.QuoteGapService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.service.StockStatsService;
import com.cspinformatique.csptrading.util.MarketUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuoteServiceImpl implements QuoteService {	
	@Autowired private ActiveTickConnector activeTickConnector;
	
	@Autowired private QuoteGapService quoteGapService;	
	@Autowired private QuoteRepository quoteRepository;
	
	@Autowired private ObjectMapper objectMapper;
	@Autowired private StockService stockService;
	@Autowired private StockStatsService stockStatsService;
	
	@Override
	public double getAverageLowQuote(Stock stock, List<Quote> quotes){
		Map<Date, Quote> lowestQuotes = new HashMap<Date, Quote>();

		for(Quote quote : quotes){
			Date date = MarketUtil.getOpeningTime(stock.getMarket(), quote.getTimestamp());
			if(!lowestQuotes.containsKey(date) || lowestQuotes.get(date).getLow() > quote.getLow()){
				lowestQuotes.put(date, quote);
			}
		}
		
		double sumLast = 0;
		int count = 0;
		
		for(Entry<Date, Quote> quoteEntry : lowestQuotes.entrySet()){			
			++count;
			sumLast +=	quoteEntry.getValue().getLow();
		}
		
		return sumLast / count;
	}
	
	@Override
	public double getBuyableQuantity(double investment, double stockPrice){
		return Math.floor(investment / stockPrice);
	}
	
	@Override
	public void loadLatestQuoteFromProvider(Stock stock){
		List<Quote> quoteForStats = new ArrayList<Quote>();
		Date startDate = null;
		Date endDate = new Date();
		Date lastQuoteTimestamp = null;
		
		try{
			// Retreiving latest date from datasource.
			if(stock.getLastQuoteTimestamp() != null){
				startDate = MarketUtil.getOpeningTime(stock.getMarket(), stock.getLastQuoteTimestamp());
			}else{
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, -8);
				startDate = MarketUtil.getOpeningTime(stock.getMarket(), MarketUtil.getOpenedDatesSinceDays(8).get(7));
			}
			
			Calendar todayCalendar = Calendar.getInstance();
			Calendar quoteCalendar = Calendar.getInstance();
			todayCalendar.setTime(new Date());
			
			List<Quote> quotes =  this.loadQuotesFromProvider(stock, startDate, endDate);
			
			for(Quote quote : quotes){
				quoteCalendar.setTime(quote.getTimestamp());
				if(todayCalendar.get(Calendar.DAY_OF_MONTH) != quoteCalendar.get(Calendar.DAY_OF_MONTH)){
					quoteForStats.add(quote);
				}
				
				if(lastQuoteTimestamp == null || lastQuoteTimestamp.getTime() < quote.getTimestamp().getTime()){
					lastQuoteTimestamp = quote.getTimestamp();
				}
			}

			// Calculating quote gaps.
			for(Date date : MarketUtil.getOpenedDates(startDate, endDate)){
				quoteGapService.generateQuoteGap(stock, quoteForStats, date);
			}
			
			if(quotes.size() > 0){
				this.saveQuotes(quotes);
				
				stock.setLastQuoteTimestamp(lastQuoteTimestamp);
				this.stockService.saveStock(stock);
				this.stockStatsService.generateStockStats(stock, MarketUtil.getOpenedDates(startDate, endDate));
			}
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public List<Quote> loadQuotesFromProvider(Stock stock, Date startDate, Date endDate){
		return new QuoteHistoryRequestor(
			stock, 
			startDate, 
			endDate,
			this.activeTickConnector.getSession()
		).requestQuotes();
	}
	
	@Override
	public void saveQuote(Quote quote) {
		this.quoteRepository.save(quote);
	}
	
	@Override
	public void saveQuotes(List<Quote> quotes) {
		this.quoteRepository.save(quotes);
	}	
}
