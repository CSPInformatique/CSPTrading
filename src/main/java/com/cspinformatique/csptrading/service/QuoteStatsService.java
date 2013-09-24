package com.cspinformatique.csptrading.service;

import java.util.Date;

import com.cspinformatique.csptrading.entity.QuoteStats;
import com.cspinformatique.csptrading.entity.Stock;

public interface QuoteStatsService {
	public void save(QuoteStats quoteStats);
	
	public void generateQuoteStats(Stock stock, Date date);
	
	public QuoteStats getQuoteStats(long stockId, Date date);
}
