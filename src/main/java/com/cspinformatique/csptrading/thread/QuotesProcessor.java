package com.cspinformatique.csptrading.thread;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cspinformatique.csptrading.entity.QuoteProcessorStats;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.service.StockService;

@Component
public class QuotesProcessor implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(QuotesProcessor.class);
	
	@Autowired private StockService stockService;
	
	private Date startTimestamp;
	private Date endTimestamp;
	
	private boolean running;
	private boolean stop;
	
	private int stocksProccessed;
	private int stocksToProccess;
	
	public QuotesProcessor(){
		logger.info("Initializing QuotesProcessorThread.");
		
		this.stocksProccessed = 0;
	}
	
	public QuoteProcessorStats getStats(){
		int progress = 0;
		if(stocksToProccess != 0){
			progress = (int)((double)stocksProccessed / (double)stocksToProccess * 100);
		}else{
			progress = 100;
		}
		
		return new QuoteProcessorStats(
			startTimestamp, 
			endTimestamp,
			progress,
			running, 
			stocksProccessed, 
			stocksToProccess
		);
	}
	
	public int getStocksProccesed(){
		return this.stocksProccessed;
	}
	
	public int getStocksToProccess(){
		return this.stocksToProccess;
	}
	
	public boolean isRunning(){
		return this.running;
	}
	
	@Override
	public void run() {
		try{
			this.running = true;
			this.stop = false;
			startTimestamp = new Date();
			
			List<Stock> stocks = this.stockService.getStocks();
			stocksToProccess = stocks.size();
			stocksProccessed = 0;
			
			for(Stock stock : stocks){					
				if(!stop){
					stockService.refreshStockQuote(stock);
					
					++stocksProccessed;
				}else{
					break;
				}
			}
			
			endTimestamp = new Date();
			logger.info("Quote processing lasted " + (endTimestamp.getTime() - startTimestamp.getTime() / 1000 / 60) + " minutes.");
		}finally{
			this.running = false;
			this.stop = false;
		}
	}
	
	public void stop(){
		this.stop = true;
	}
}