package com.cspinformatique.csptrading.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.csptrading.entity.Investment;
import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.service.InvestmentService;
import com.cspinformatique.csptrading.service.QuoteGapService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockService;

@Controller
@RequestMapping("/tools")
public class ToolsController {
	@Autowired private InvestmentService investmentService;
	@Autowired private QuoteGapService quoteGapService;
	@Autowired private QuoteService quoteService;
	@Autowired private StockService stockService;
	
	private DecimalFormat doubleDecimalFormat;
	private DecimalFormat intDecimalFormat;
	
	public ToolsController(){
		this.doubleDecimalFormat = new DecimalFormat("0.00");
		this.intDecimalFormat = new DecimalFormat("0");
	}
	
	@RequestMapping(params="calculateBuyableQuantity")
	public @ResponseBody String calculateBuyableQuantity(double price, double investmentAmount){
		return intDecimalFormat.format(
			this.quoteService.getBuyableQuantity(investmentAmount, price)
		);
	}
	
	@RequestMapping(params="calculateInvestment")
	public @ResponseBody String calculateInvestment(double buyPrice, double sellPrice, int quantity, double brokerFees){
		return doubleDecimalFormat.format(
			this.investmentService.calculateInvestment(
				new Investment(
					null, 
					new StockOrder(null, buyPrice, brokerFees, quantity), 
					new StockOrder(null, sellPrice, brokerFees, quantity)
				)
			)
		);
	}
	
	@RequestMapping
	public String getToolsPage(){
		return "tools";
	}
}
