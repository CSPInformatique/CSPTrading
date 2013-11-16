package com.cspinformatique.csptrading.repository.sql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.StocksAnalysis;
import com.cspinformatique.csptrading.entity.StocksAnalysisResult;

public interface StocksAnalysisResultRepository extends JpaRepository<StocksAnalysisResult, Integer> {
	public List<StocksAnalysisResult> findByStocksAnalysis(StocksAnalysis stocksAnalysis);
}