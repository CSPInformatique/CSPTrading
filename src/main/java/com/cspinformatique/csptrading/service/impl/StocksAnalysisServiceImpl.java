package com.cspinformatique.csptrading.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.activetick.ActiveTickConnector;
import com.cspinformatique.csptrading.activetick.QuoteRequestor;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.entity.StocksAnalysis;
import com.cspinformatique.csptrading.entity.StocksAnalysisResult;
import com.cspinformatique.csptrading.repository.sql.StocksAnalysisRepository;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StocksAnalysisResultService;
import com.cspinformatique.csptrading.service.StocksAnalysisService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.thread.StocksAnalysisThread;

@Service
public class StocksAnalysisServiceImpl implements StocksAnalysisService {
	private static final Logger logger = LoggerFactory.getLogger(StocksAnalysisServiceImpl.class);
	
	@Autowired private QuoteService quoteService;
	@Autowired private StocksAnalysisResultService stocksAnalysisResultService;
	@Autowired private StockService stockService;
	
	@Autowired private ActiveTickConnector activeTickConnector;
	
	@Autowired private StocksAnalysisRepository stocksAnalysisRepository;
	
	private Map<Integer, StocksAnalysisThread> threads;
	
	public StocksAnalysisServiceImpl() {
		this.threads = new HashMap<Integer, StocksAnalysisThread>();
	}
	
	@Override
	public StocksAnalysis find(int stocksAnalysisId){
		StocksAnalysisThread thread = this.threads.get(stocksAnalysisId);
		
		if(thread != null){
			return thread.getStocksAnalysis();
		}else{
			return stocksAnalysisRepository.findOne(stocksAnalysisId);
		}
	}
	
	@Override
	public List<StocksAnalysis> findAll(){
		return this.stocksAnalysisRepository.findAll();
	}
	
	@Override
	public void generateAnalysis(StocksAnalysis stocksAnalysis){
		// Retreiving every stocks.
		List<Stock> stocks = stockService.getStocks();
		
		stocksAnalysis.setStocksToProcess(stocks.size());
		for(Stock stock : stockService.getStocks()){
			
			List<Quote> quotes =	this.quoteService.loadQuotesFromProvider(
										stock, 
										stocksAnalysis.getStartDate(), 
										stocksAnalysis.getEndDate()
									);
			
			// Calculating the average quote.
			double total = 0;
			double lowestQuote = 0;
			for(Quote quote : quotes){
				total += quote.getLow();
				if(lowestQuote == 0 || quote.getLow() < lowestQuote){
					lowestQuote = quote.getLow();
				}
			}
			double quoteAverage = total / quotes.size();
			
			double lastQuote = new QuoteRequestor(stock, activeTickConnector.getSession()).requestQuote();
			
			double cycleTarget = lowestQuote * ((stocksAnalysis.getCyclesMarginPercent() / 100) + 1);
			logger.info(
				"Lowest quote : " + lowestQuote + " - " + " | " +
				"Cycle target : " + cycleTarget
			);
			
			if(lastQuote < quoteAverage){
				// Calculating cycles.
				int cyclesCount = 0;
				boolean lowReached = false;
				for(Quote quote : quotes){
					// cycle count activate itself when the lowest quote + cycle margin is reached.
					if(!lowReached){
						if(quote.getLow() <= cycleTarget){
							logger.info("Cycle starting at " + quote.getLow() + " (" + quote.getTimestamp() + ")");
							lowReached = true;
						}
					}else{
						if(quote.getLow() >= quoteAverage){
							logger.info("Cycle completed reached at " + quote.getLow() + " (" + quote.getTimestamp() + ")");
							++cyclesCount;
							lowReached = false;
						}
					}
				}
				
				logger.info(cyclesCount + " calculated for " + stock.getSymbol() + ".");

				if(cyclesCount > 0){
					stocksAnalysisResultService.save(
						new StocksAnalysisResult(
							0, 
							stocksAnalysis, 
							stock, 	
							cyclesCount, 
							quoteAverage - cycleTarget, 
							quoteAverage,
							lastQuote
						)
					);
				}
			}else{
				logger.info("Skipping " + stock.getSymbol() + ", last quote higher than average.");
			}
			
			stocksAnalysis.setStocksProcessed(stocksAnalysis.getStocksProcessed() + 1);
			
			this.stocksAnalysisRepository.save(stocksAnalysis);
		}
	}
	
	@Override
	public void removeStocksAnalysisThreadFromBuffer(int stocksAnalysisId){
		this.threads.remove(stocksAnalysisId);
	}
	
	@Override
	public StocksAnalysis startAnalysis(StocksAnalysis stocksAnalysis){
		stocksAnalysis = this.stocksAnalysisRepository.save(stocksAnalysis);

		StocksAnalysisThread stocksAnalysisThread = new StocksAnalysisThread(stocksAnalysis, this);
		
		new Thread(stocksAnalysisThread).start();
		
		this.threads.put(stocksAnalysis.getId(), stocksAnalysisThread);
		
		return stocksAnalysis;
	}
}
