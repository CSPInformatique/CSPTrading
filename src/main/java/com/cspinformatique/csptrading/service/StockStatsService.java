package com.cspinformatique.csptrading.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.csptrading.entity.StockStats;
import com.cspinformatique.csptrading.entity.Stock;

public interface StockStatsService {
	public void save(StockStats stockStats);
	
	public void generateStockStats(Stock stock, Date date);
	
	public void generateStockStats(Stock stock, List<Date> dates);
	
	public List<StockStats> getStocksStats(Date date);
	
	public StockStats getStockStats(long stockId, Date date);
}
