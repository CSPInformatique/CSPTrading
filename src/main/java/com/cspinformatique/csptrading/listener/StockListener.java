package com.cspinformatique.csptrading.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.service.QuoteService;

@Component
public class StockListener implements EntityListener<Stock>{
	@Autowired private QuoteService quoteService;
	
	@Override
	public void handlePostLoad(Stock stock) {
		stock.setLastQuote(quoteService.loadLatestQuoteFromProvider(stock));
	}
}
