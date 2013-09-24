package com.cspinformatique.csptrading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.QuoteResponse;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.util.MarketUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuoteServiceImpl implements QuoteService {
	private static final String LECHO_URL = "http://1.ajax.lecho.be/rtq/?reqtype=simple&quotes=";
	
	@Autowired private com.cspinformatique.csptrading.repository.mongo.QuoteRepository quoteRepository;
	
	@Autowired private RestTemplate restTemplate;
	@Autowired private ObjectMapper objectMapper;
	@Autowired private StockService stockService;
	
	@Override
	public double getAverageLowQuote(Stock stock, List<Date> dates){
		Market market = stock.getMarket();
		
		double sumLast = 0;
		int count = 0;
		for(Date date : dates){
			Quote quote =	this.findLastQuoteBetweenDates(
								stock.getId(), 
								MarketUtil.getOpeningTime(market, date), 
								MarketUtil.getClosingTime(market, date)
							);
			
			if(quote != null){
				++count;
				sumLast +=	quote.getLow();
			}
		}
		
		return sumLast / count;
	}
	
	@Override
	public double getBuyableQuantity(double investment, double stockPrice){
		return Math.floor(investment / stockPrice);
	}
	
	@Override
	public List<Quote> findByStockIdAndTimestampBetween(long stockId, Date fromDate, Date toDate){
		return this.quoteRepository.findByStockIdAndTimestampBetweenOrderByTimestampAsc(stockId, fromDate, toDate);
	}
	
	@Override
	public Quote findLastQuote(long stockId){
		return this.quoteRepository.findLastQuote(stockId);
	}
	
	@Override
	public Quote findLastQuoteBetweenDates(long stockId, Date fromDate, Date toDate){
		return this.quoteRepository.findLastQuoteBetweenDates(stockId, fromDate, toDate);
	}
	
	@Override
	public Quote getQuote(String id){
		return this.quoteRepository.findOne(id);
	}
	
	@Override
	public Quote loadLatestQuoteFromProvider(Stock stock){
		try{
			// Retreiving LECHO' web services and removing the head of the response.
			String response =	new RestTemplate().getForObject(
									LECHO_URL + stock.getId(),
									String.class
								).substring(16);
			
			// Converting the message after removing the tail of the response.
			QuoteResponse quoteResponse =	objectMapper.readValue(
												response.substring(0, response.length()-19), 
												QuoteResponse.class
											);
			
			Quote quote =	quoteResponse.getStocks().get(
								stock.getId()
							);
			
			quote.setStockId(stock.getId());
			quote.setTimestamp(new Date(quoteResponse.getServerTime()));
			
			return quote;
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public void saveQuote(Quote quote) {
		this.quoteRepository.save(quote);
	}
}
