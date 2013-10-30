package com.cspinformatique.csptrading.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.cspinformatique.csptrading.entity.QuoteProcessorStats;
import com.cspinformatique.csptrading.service.StockService;

@Controller
@RequestMapping("/quoteProcessor")
public class QuoteProcessorController {
	@Autowired private StockService stockService;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(params="start")
	public void startQuoteProcessor(){
		this.stockService.startQuoteProcessor();
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String getQuoteProcessorPage(){
		return "quoteProcessor";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody QuoteProcessorStats getStats(){
		return this.stockService.getQuotesProcessor().getStats();
	}
}
