package com.cspinformatique.csptrading.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.MarketStocks;
import com.cspinformatique.csptrading.entity.QuoteGap;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.sql.StockRepository;
import com.cspinformatique.csptrading.service.MarketService;
import com.cspinformatique.csptrading.service.QuoteGapService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.QuoteStatsService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.thread.QuotesProcessorThread;

@Service
public class StockServiceImpl implements StockService {
	@Autowired private MarketService marketService;
	@Autowired private StockRepository stockRepository;
	@Autowired private QuoteGapService quoteGapService;
	@Autowired private QuoteService quoteService;
	@Autowired private QuoteStatsService quoteStatsService;
	
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
			QuoteGap quoteGap = this.quoteGapService.getQuoteGap(stock, date);
			
			if(largestQuoteGap == null || quoteGap.getGap() > largestQuoteGap.getGap()){
				stockWithLargestQuoteGap = stock;
				largestQuoteGap = quoteGap;
			}
		}
		
		return stockWithLargestQuoteGap;
	}
	
	@Override
	public double getAverageLowQuote(Stock stock, List<Date> dates){
		return this.quoteService.getAverageLowQuote(stock, dates);
	}
	
	@Override
	public List<MarketStocks> getMarketsStocks(){
		List<MarketStocks> marketsStocks = new ArrayList<MarketStocks>();
		for(Market market : marketService.getMarkets()){
			marketsStocks.add(
				new MarketStocks(market, this.stockRepository.findByMarket(market))
			);
		}
		
		return marketsStocks;
	}
	
	@Override
	public Stock getStock(long stockId){
		return this.stockRepository.findOne(stockId);
	}
	
	@Override
	public Stock getStock(String symbol){
		return this.stockRepository.findBySymbol(symbol);
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
		quoteService.saveQuote(quoteService.loadLatestQuoteFromProvider(stock));
		
		stock = this.saveStock(stock);
		
		quoteStatsService.generateQuoteStats(stock, new Date());
	}
	
	@Override
	public Stock saveStock(Stock stock) {
		return this.stockRepository.save(stock);
	}
}
