package com.cspinformatique.csptrading.thread;

//import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.service.StockService;

@Component
public class QuotesWithPositionProcessor implements Runnable {	
	@Autowired private StockService stockService;
	
	private boolean stop;
	
	@Override
	public void run() {
		stop = false;
		try{
			do{
				for(Stock stock : this.stockService.findStocksWithPositions()){					
					if(!stop){
						stockService.refreshStockQuote(stock);
					}else{
						break;
					}
				}
				
				Thread.sleep(60000);
			}while(!stop);
		}catch(InterruptedException interruptedEx){
			throw new RuntimeException(interruptedEx);
		}
	}
	
	public void stop(){
		this.stop = true;
	}
}