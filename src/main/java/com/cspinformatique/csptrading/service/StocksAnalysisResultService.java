package com.cspinformatique.csptrading.service;

import java.util.List;

import com.cspinformatique.csptrading.entity.StocksAnalysis;
import com.cspinformatique.csptrading.entity.StocksAnalysisResult;

public interface StocksAnalysisResultService {
	public List<StocksAnalysisResult> findByStocksAnalysis(StocksAnalysis stocksAnalysis);
	
	public StocksAnalysisResult save(StocksAnalysisResult stocksAnalysisResult);
}
