package com.cspinformatique.csptrading.service;

import java.util.List;

import com.cspinformatique.csptrading.entity.StocksAnalysis;

public interface StocksAnalysisService {
	public StocksAnalysis find(int stocksAnalysisId);
	
	public List<StocksAnalysis> findAll();
	
	public void generateAnalysis(StocksAnalysis stocksAnalysis);
	
	public void removeStocksAnalysisThreadFromBuffer(int stocksAnalysisId);
	
	public StocksAnalysis startAnalysis(StocksAnalysis stocksAnalysis);
}
