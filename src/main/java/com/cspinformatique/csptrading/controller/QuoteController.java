package com.cspinformatique.csptrading.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;

@Controller
@RequestMapping("/quote")
public class QuoteController extends CspTradingController {
	@Autowired private QuoteGapController quoteGapController;
	
	@Autowired private QuoteService quoteService;
	@Autowired private StockService stockService;
	
	private DateFormat dateFormat;
	
	public QuoteController(){
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{stockId}", params={"symbol", "date"})
	public @ResponseBody Quote getQuote(@RequestParam String symbol, @RequestParam String date){
		try{
			Calendar fromDate = Calendar.getInstance();
			Calendar toDate = Calendar.getInstance();
			Date parsedDate = dateFormat.parse(date);
			
			fromDate.setTime(parsedDate);
			
			toDate.setTime(parsedDate);
			toDate.add(Calendar.DAY_OF_MONTH, 1);
			
			return quoteService.findLastQuoteBetweenDates(
				this.stockService.getStock(symbol).getId(), 
				fromDate.getTime(), 
				toDate.getTime()
			);
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}
	
	@RequestMapping
	public String getQuoteGap(Model model){		
		return "quote";
	}
}
