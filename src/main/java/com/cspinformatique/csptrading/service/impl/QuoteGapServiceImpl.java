package com.cspinformatique.csptrading.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.QuoteGap;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.sql.QuoteGapRepository;
import com.cspinformatique.csptrading.service.QuoteGapService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.util.MarketUtil;

@Service
public class QuoteGapServiceImpl implements QuoteGapService {
	private static final Logger logger = LoggerFactory.getLogger(QuoteGapServiceImpl.class);
	
	@Autowired private QuoteGapRepository quoteGapRepository;
	@Autowired private QuoteService quoteService;
	@Autowired private StockService stockService;
	
	@Override
	public void generateQuoteGap(Stock stock, List<Quote> quotes, Date date){
		Quote maxQuote = null;
		Quote minQuote = null;
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		
		Market market = stock.getMarket();
		
		for(Quote quote : quotes){
			if(	(minQuote == null || minQuote.getClose() > quote.getClose()) &&
					(maxQuote == null || maxQuote.getTimestamp().getTime() > quote.getTimestamp().getTime())
			){
				minQuote = quote;
			}
			
			if(	(maxQuote == null || maxQuote.getClose() < quote.getClose()) && 
					minQuote.getTimestamp().getTime() < quote.getTimestamp().getTime()
			){
				maxQuote = quote;
			}
		}
		
		if(minQuote != null && maxQuote != null){
			logger.info("Persisting quote gap calculation for " + stock.getSymbol());
			
			this.quoteGapRepository.save(
				new QuoteGap(
					stock,
					MarketUtil.getClosingTime(market, date), 
					(maxQuote.getClose() * 100 / minQuote.getClose()) - 100
				)
			);
		}
	}
	
	@Override
	public QuoteGap getQuoteGap(Stock stock, Date date){
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		
		logger.info("Retreiving quote gap for " + stock.getSymbol() + " on " + date);
		
		return quoteGapRepository.findByStockIdAndDate(stock.getId(), MarketUtil.getClosingTime(stock.getMarket(), date));
	}
}
