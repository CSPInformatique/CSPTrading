package com.cspinformatique.csptrading.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.QuoteGap;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.sql.StockRepository;
import com.cspinformatique.csptrading.service.QuoteGapService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.thread.QuotesProcessorThread;

@Service
public class StockServiceImpl implements StockService {
	@Autowired private StockRepository stockRepository;
	@Autowired private QuoteGapService quoteGapService;
	@Autowired private QuoteService quoteService;
	
	private QuotesProcessorThread quotesProcessorsThread;
	
	@Override
	public List<Stock> findStocksToRefresh() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -1);
		
		return this.stockRepository.findByLastQuoteTimestampLessThanOrLastQuoteTimestampIsNull(calendar.getTime());
	}
	
	@Override
	public Stock findStockWithLargestQuoteGap(Date date){
		Stock stockWithLargestQuoteGap = null;
		QuoteGap largestQuoteGap = null;
		
		for(Stock stock : this.stockRepository.findAll()){
			QuoteGap quoteGap = this.quoteGapService.getQuoteGap(stock.getId(), date);
			
			if(largestQuoteGap == null || quoteGap.getGap() > largestQuoteGap.getGap()){
				stockWithLargestQuoteGap = stock;
				largestQuoteGap = quoteGap;
			}
		}
		
		return stockWithLargestQuoteGap;
	}
	
	@Override
	public Stock getStock(long stockId){
		return this.stockRepository.findOne(stockId);
	}
	
	@Override
	public List<Stock> getStocks(){
		return this.stockRepository.findAll();
	}
	
	@PostConstruct
	public void init(){
		this.launchQuoteProcessor();
	}
	
	private void launchQuoteProcessor(){
		this.quotesProcessorsThread = new QuotesProcessorThread(this);
		new Thread(this.quotesProcessorsThread).start();
	}
	
	@Override
	public void refreshStockQuote(Stock stock) {
		// Retreive the latest quote before persisting it.
		quoteService.saveQuote(quoteService.loadLatestQuote(stock));
		
		// Update the last quote timestamp.
		stock.setLastQuoteTimestamp(new Date());
		this.saveStock(stock);
	}
	
	@Override
	public void saveStock(Stock stock) {
		this.stockRepository.save(stock);
	}
}
