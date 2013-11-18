package com.cspinformatique.csptrading.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.MarketStocks;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.StockSearchResult;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController extends CspTradingController {
	@Autowired private QuoteService quoteService; 
	@Autowired private StockService stockService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json", params="market")
	public @ResponseBody List<MarketStocks> getMarketsStocks(){
		return this.stockService.getMarketsStocks();
	}
	
	@RequestMapping(value="/{symbol}/chart", method=RequestMethod.GET)
	public String getStockChartPage(@PathVariable String symbol, Model model){
		model.addAttribute("symbol", symbol);
		
		return "stock";
	}
	
	@RequestMapping(value="/{symbol}/chart", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Object[]> getStockChartData(@PathVariable String symbol){
		ArrayList<Object[]> results = new ArrayList<Object[]>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		
		for(Quote quote :	this.quoteService.loadQuotesFromProvider(
								this.stockService.getStock(symbol), calendar.getTime(), new Date(), (short)60
							)
		){
			results.add(
				new Object[]{
					quote.getTimestamp().getTime(), 
					quote.getLow()
				}
			); 
		}
		
		return results;
	}
	
	@RequestMapping
	public String getStocksPage(){
		return "stocks";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody StockSearchResult getStocks(@RequestParam Integer pageIndex, @RequestParam Integer resultsPerPage){
		return this.stockService.getStocks(pageIndex, resultsPerPage);
	}
}
