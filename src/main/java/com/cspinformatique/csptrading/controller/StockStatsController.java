package com.cspinformatique.csptrading.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.StockStats;
import com.cspinformatique.csptrading.service.StockStatsService;
import com.cspinformatique.csptrading.util.MarketUtil;

@Controller
@RequestMapping("/stockStats")
public class StockStatsController {
	@Autowired private StockStatsService stockStatsSercice;
	
	private DateFormat dateFormat;
	
	public StockStatsController(){
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<StockStats> getStocksStats(){
		try{
			return stockStatsSercice.getStocksStats(dateFormat.parse(dateFormat.format(MarketUtil.getOpenedDatesSinceDays(1).get(0))));
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(params={"date"}, method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<StockStats> getStocksStats(@RequestParam String date){
		try{			
			return stockStatsSercice.getStocksStats(dateFormat.parse(date));
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}
	
	@RequestMapping
	public String getStocksStatsPage(){
		return "stock/stocksStats";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{stockId}", params={"date"}, method=RequestMethod.GET, produces="application/json")
	public @ResponseBody StockStats getStockStats(@PathVariable long stockId, @RequestParam String date){
		try{			
			return stockStatsSercice.getStockStats(stockId, dateFormat.parse(date));
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}
}
