package com.cspinformatique.csptrading.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.QuoteGap;
import com.cspinformatique.csptrading.service.QuoteGapService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.util.MarketUtil;

@Service
public class QuoteGapServiceImpl implements QuoteGapService {
	@Autowired private QuoteService quoteService;
	@Autowired private StockService stockService;
	
	@Override
	public QuoteGap getQuoteGap(long stockId, Date date){
		Quote maxQuote = null;
		Quote minQuote = null;
		
		Market market = stockService.getStock(stockId).getMarket();
		
		for(Quote quote : 
				this.quoteService.findByStockIdAndTimestampBetween(
					stockId, 
					MarketUtil.getOpeningTime(market, date), 
					MarketUtil.getClosingTime(market, date)
				)
		){
			if(	(minQuote == null || minQuote.getLast() > quote.getLast()) &&
					(maxQuote == null || maxQuote.getTimestamp().getTime() > quote.getTimestamp().getTime())
			){
				minQuote = quote;
			}
			
			if(	(maxQuote == null || maxQuote.getLast() < quote.getLast()) && 
					minQuote.getTimestamp().getTime() < quote.getTimestamp().getTime()
			){
				maxQuote = quote;
			}
		}
		
		if(minQuote != null && maxQuote != null){
			System.out.println(
				"StockId : " + stockId +
				" | Min : " + minQuote.getLast() + 
				" | Max : " + maxQuote.getLast() + 
				" | % : " + ((maxQuote.getLast() * 100 / minQuote.getLast()) - 100)
			);
			
			return new QuoteGap(stockId, date, (maxQuote.getLast() * 100 / minQuote.getLast()) - 100);
		}else{
			return null;
		}
	}

}
