package com.cspinformatique.csptrading.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.activetick.ActiveTickConnector;
import com.cspinformatique.csptrading.activetick.QuoteHistoryRequestor;
import com.cspinformatique.csptrading.activetick.QuoteRequestor;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.util.MarketUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuoteServiceImpl implements QuoteService {	
	@Autowired private ActiveTickConnector activeTickConnector;
	
	@Autowired private ObjectMapper objectMapper;
	@Autowired private StockService stockService;
	
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
	public Quote loadLatestQuoteFromProvider(Stock stock){
		return new QuoteRequestor(stock, activeTickConnector.getSession()).requestQuote();
	}
	
	@Override
	public List<Quote> loadQuotesFromProvider(Stock stock, Date startDate, Date endDate, short intradayMinuteCompression){
		return new QuoteHistoryRequestor(
			stock, 
			startDate, 
			endDate,
			intradayMinuteCompression,
			this.activeTickConnector.getSession()
		).requestQuotes();
	}	
}
