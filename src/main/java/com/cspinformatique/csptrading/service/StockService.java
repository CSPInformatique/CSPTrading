package com.cspinformatique.csptrading.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.csptrading.entity.Stock;

public interface StockService {
	public List<Stock> findStocksToRefresh();
	
	public Stock findStockWithLargestQuoteGap(Date date);
	
	public Stock getStock(long stockId);
	
	public List<Stock> getStocks();
	
	public void refreshStockQuote(Stock stock);
	
	public void saveStock(Stock stock);	
}
