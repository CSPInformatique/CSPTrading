package com.cspinformatique.csptrading.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;

public interface QuoteService{	
	public double getAverageLowQuote(Stock stock, List<Quote> quotes);
	
	public double getBuyableQuantity(double investment, double price);
	
	public Quote loadLatestQuoteFromProvider(Stock stock);
	
	public List<Quote> loadQuotesFromProvider(Stock stock, Date startDate, Date endDate, short intradayMinuteCompression);
}
