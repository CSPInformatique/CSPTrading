package com.cspinformatique.csptrading.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.csptrading.entity.MarketStocks;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.thread.QuotesProcessor;

public interface StockService {
	public List<Stock> findStocksWithPositions();
	
	public Stock findStockWithLargestQuoteGap(Date date);
	
	public double getAverageLowQuote(Stock stock, List<Quote> quotes);
	
	public List<MarketStocks> getMarketsStocks();
	
	public QuotesProcessor getQuotesProcessor();
	
	public Stock getStock(long stockId);
	
	public Stock getStock(String symbol);
	
	public List<Stock> getStocks();
	
	public void refreshStockQuote(Stock stock);
	
	public Stock saveStock(Stock stock);	
	
	public void startQuoteProcessor();
}
