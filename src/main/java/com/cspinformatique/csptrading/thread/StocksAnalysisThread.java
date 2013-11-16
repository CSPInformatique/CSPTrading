package com.cspinformatique.csptrading.thread;

import com.cspinformatique.csptrading.entity.StocksAnalysis;
import com.cspinformatique.csptrading.service.StocksAnalysisService;

public class StocksAnalysisThread implements Runnable {
	private StocksAnalysis stocksAnalysis;
	private StocksAnalysisService stocksAnalysisService;
	
	public StocksAnalysisThread(
		StocksAnalysis stocksAnalysis, 
		StocksAnalysisService stocksAnalysisService
	){
		this.stocksAnalysis = stocksAnalysis;
		this.stocksAnalysisService = stocksAnalysisService;
	}
	
	public StocksAnalysis getStocksAnalysis(){
		return this.stocksAnalysis;
	}
	
	@Override
	public void run() {
		try{
			this.stocksAnalysisService.generateAnalysis(stocksAnalysis);
		}finally{
			this.stocksAnalysisService.removeStocksAnalysisThreadFromBuffer(stocksAnalysis.getId());
		}
	}

}
