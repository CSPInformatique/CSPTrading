package com.cspinformatique.csptrading.service.impl;

import java.util.Calendar;
import java.util.Date;

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
	@Autowired private QuoteGapRepository quoteGapRepository;
	@Autowired private QuoteService quoteService;
	@Autowired private StockService stockService;
	
	@Override
	public QuoteGap getQuoteGap(Stock stock, Date date){
		Quote maxQuote = null;
		Quote minQuote = null;
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		
		Market market = stock.getMarket();
		
		QuoteGap quoteGap = quoteGapRepository.findByStockIdAndDate(stock.getId(), MarketUtil.getClosingTime(market, date));
		if(quoteGap == null || !MarketUtil.getClosingTime(market, date).before(MarketUtil.getClosingTime(market, new Date()))){
			for(Quote quote : 
					this.quoteService.findByStockIdAndTimestampBetween(
						stock.getId(), 
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
					"StockId : " + stock.getId() +
					" | Min : " + minQuote.getLast() + 
					" | Max : " + maxQuote.getLast() + 
					" | % : " + ((maxQuote.getLast() * 100 / minQuote.getLast()) - 100)
				);
				
				return this.quoteGapRepository.save(
					new QuoteGap(0, stock.getId(), MarketUtil.getClosingTime(market, date), (maxQuote.getLast() * 100 / minQuote.getLast()) - 100)
				);
			}else{
				return null;
			}
		}else{
			return quoteGap;
		}
	}
}
