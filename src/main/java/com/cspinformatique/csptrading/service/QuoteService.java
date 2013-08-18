package com.cspinformatique.csptrading.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;

public interface QuoteService{
	public double getBuyableQuantity(double investment, double price);
	
	public Quote getQuote(String id);
	
	public List<Quote> findByStockIdAndTimestampBetween(long stockId, Date fromDate, Date toDate);
	
	public Quote loadLatestQuote(Stock stock);
	
	public void saveQuote(Quote quote);
}
