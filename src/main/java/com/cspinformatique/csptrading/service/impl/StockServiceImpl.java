package com.cspinformatique.csptrading.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.MarketStocks;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.QuoteGap;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.sql.StockRepository;
import com.cspinformatique.csptrading.service.MarketService;
import com.cspinformatique.csptrading.service.QuoteGapService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockStatsService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.thread.QuotesProcessor;
import com.cspinformatique.csptrading.thread.QuotesWithPositionProcessor;

@Service
public class StockServiceImpl implements StockService {
	@Autowired private MarketService marketService;
	@Autowired private StockRepository stockRepository;
	@Autowired private QuoteGapService quoteGapService;
	@Autowired private QuoteService quoteService;
	@Autowired private StockStatsService stockStatsService;
	
	@Autowired private QuotesProcessor quotesProcessor;
	
	@Autowired private QuotesWithPositionProcessor quotesWithPositionProcessor;
	
	@Override
	public List<Stock> findStocksWithPositions() {		
		return this.stockRepository.findWithPositions();
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
	public double getAverageLowQuote(Stock stock, List<Quote> quotes){
		return this.quoteService.getAverageLowQuote(stock, quotes);
	}
	
	@Override
	public QuotesProcessor getQuotesProcessor(){
		return this.quotesProcessor;
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
		new Thread(this.quotesWithPositionProcessor).start();
	}
	
	@Override
	public void refreshStockQuote(Stock stock) {
		// Retreive the latest quote before persisting it.
		quoteService.loadLatestQuoteFromProvider(stock);
	}
	
	@Override
	public Stock saveStock(Stock stock) {
		return this.stockRepository.save(stock);
	}
	
	@Override
	public void startQuoteProcessor(){
		if(!this.quotesProcessor.isRunning()){
			new Thread(this.quotesProcessor).start();
		}else{
			throw new RuntimeException("Quote processor is already running.");
		}
	}
}
