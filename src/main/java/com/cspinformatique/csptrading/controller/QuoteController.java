package com.cspinformatique.csptrading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;

@Controller
@RequestMapping("/quote")
public class QuoteController extends CspTradingController {	
	@Autowired private QuoteService quoteService;
	@Autowired private StockService stockService;
	
	@RequestMapping
	public String getQuoteLowsPage(){		
		return "quotesLows";
	}
}
