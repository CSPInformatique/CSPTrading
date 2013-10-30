package com.cspinformatique.csptrading.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.QuoteGap;
import com.cspinformatique.csptrading.entity.Stock;

public interface QuoteGapService {
	public void generateQuoteGap(Stock stock, List<Quote> quotes, Date date);
	
	public QuoteGap getQuoteGap(Stock stock, Date date);
}
