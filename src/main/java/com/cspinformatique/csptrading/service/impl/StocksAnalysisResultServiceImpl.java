package com.cspinformatique.csptrading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.StocksAnalysis;
import com.cspinformatique.csptrading.entity.StocksAnalysisResult;
import com.cspinformatique.csptrading.repository.sql.StocksAnalysisResultRepository;
import com.cspinformatique.csptrading.service.StocksAnalysisResultService;

@Service
public class StocksAnalysisResultServiceImpl implements StocksAnalysisResultService {
	@Autowired private StocksAnalysisResultRepository stocksAnalysisResultRepository;
	
	@Override
	public List<StocksAnalysisResult> findByStocksAnalysis(StocksAnalysis stocksAnalysis) {
		return this.stocksAnalysisResultRepository.findByStocksAnalysis(stocksAnalysis);
	}
	
	@Override
	public StocksAnalysisResult save(StocksAnalysisResult stocksAnalysisResult){
		return this.stocksAnalysisResultRepository.save(stocksAnalysisResult);
	}
}
