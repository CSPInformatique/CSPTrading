package com.cspinformatique.csptrading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.csptrading.entity.Stock;

@Controller
@RequestMapping("/stock")
public class StockController {
	
	@RequestMapping
	public @ResponseBody Stock getStockModel(){
		return new Stock();
	}
}
