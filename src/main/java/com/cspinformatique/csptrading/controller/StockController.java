package com.cspinformatique.csptrading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.MarketStocks;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController extends CspTradingController {
	@Autowired private StockService stockService;
	
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String getStocksPage(){
		return "stocks";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json", params="market")
	public @ResponseBody List<MarketStocks> getMarketsStocks(){
		return this.stockService.getMarketsStocks();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Stock> getStocks(){
		return this.stockService.getStocks();
	}
}
