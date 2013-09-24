package com.cspinformatique.csptrading.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.QuoteStats;
import com.cspinformatique.csptrading.service.QuoteStatsService;

@Controller
@RequestMapping("/quoteStats")
public class QuoteStatsController {
	@Autowired private QuoteStatsService quoteStatsSercice;
	
	private DateFormat dateFormat;
	
	public QuoteStatsController(){
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{stockId}", params={"date"})
	public @ResponseBody QuoteStats getQuote(@PathVariable long stockId, @RequestParam String date){
		try{			
			return quoteStatsSercice.getQuoteStats(stockId, dateFormat.parse(date));
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}
}
