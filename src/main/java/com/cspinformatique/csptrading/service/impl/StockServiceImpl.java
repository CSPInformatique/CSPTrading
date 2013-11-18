package com.cspinformatique.csptrading.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.MarketStocks;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.entity.StockSearchResult;
import com.cspinformatique.csptrading.repository.sql.StockRepository;
import com.cspinformatique.csptrading.service.MarketService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.thread.QuotesProcessor;
import com.cspinformatique.csptrading.thread.QuotesWithPositionProcessor;

@Service
public class StockServiceImpl implements StockService {
	@Autowired private MarketService marketService;
	@Autowired private StockRepository stockRepository;
	@Autowired private QuoteService quoteService;
	
	@Autowired private QuotesProcessor quotesProcessor;
	
	@Autowired private QuotesWithPositionProcessor quotesWithPositionProcessor;
	
	@Override
	public List<Stock> findStocksWithPositions() {		
		return this.stockRepository.findWithPositions();
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
	public StockSearchResult getStocks(int pageIndex, int resultsPerPage){
		Page<Stock> pageResult = this.stockRepository.findAll(new PageRequest(pageIndex, resultsPerPage));
		
		return new StockSearchResult(
			pageResult.getTotalElements(), 
			pageResult.getContent(), 
			pageIndex, pageResult.getTotalPages()
		);
	}
	
	@Override
	public List<String> getSymbols(){
		return this.stockRepository.findSymbols();
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
