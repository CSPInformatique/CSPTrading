package com.cspinformatique.csptrading.thread;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.service.StockService;

public class QuotesProcessorThread implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(QuotesProcessorThread.class);
	
	private StockService stockService;
	
	private Calendar closing;
	private Calendar opening;
	
	private boolean stop;
	
	public QuotesProcessorThread(StockService stockService){
		logger.info("Initializing QuotesProcessorThread.");
		
		this.stockService = stockService;
		
		opening = Calendar.getInstance();
		closing = Calendar.getInstance();
		
		opening.set(Calendar.HOUR_OF_DAY, 9);
		opening.set(Calendar.MINUTE, 0);
		opening.set(Calendar.SECOND, 0);
		opening.set(Calendar.MILLISECOND, 0);
		
		closing.set(Calendar.HOUR_OF_DAY, 17);
		closing.set(Calendar.MINUTE, 30);
		closing.set(Calendar.SECOND, 0);
		closing.set(Calendar.MILLISECOND, 0);
	}
	
	@Override
	public void run() {
		try{
			do{
				Calendar currentTime = Calendar.getInstance();
				
				opening.set(Calendar.YEAR, currentTime.get(Calendar.YEAR));
				opening.set(Calendar.MONTH, currentTime.get(Calendar.MONTH));
				opening.set(Calendar.DAY_OF_MONTH, currentTime.get(Calendar.DAY_OF_MONTH));
				
				closing.set(Calendar.YEAR, currentTime.get(Calendar.YEAR));
				closing.set(Calendar.MONTH, currentTime.get(Calendar.MONTH));
				closing.set(Calendar.DAY_OF_MONTH, currentTime.get(Calendar.DAY_OF_MONTH));

				if(	currentTime.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && 
				currentTime.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && 
					currentTime.getTime().getTime() > opening.getTime().getTime() && 
					currentTime.getTime().getTime() < closing.getTime().getTime()
				){
					for(Stock stock : this.stockService.findStocksToRefresh()){
						logger.info("Retreiving quotations for " + stock.getSymbol());
						
						if(!stop){
							stockService.refreshStockQuote(stock);
						}else{
							break;
						}
					}
				}
				
				Thread.sleep(1000);
			}while(!stop);
		}catch(InterruptedException interruptedEx){
			throw new RuntimeException(interruptedEx);
		}
	}
	
	public void stop(){
		this.stop = true;
	}
}
