package com.cspinformatique.csptrading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.csptrading.entity.MarketStocks;
import com.cspinformatique.csptrading.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController extends CspTradingController {
	@Autowired private StockService stockService;
	
	@RequestMapping
	public @ResponseBody List<MarketStocks> getStocks(){
		return this.stockService.getMarketsStocks();
	}
}
