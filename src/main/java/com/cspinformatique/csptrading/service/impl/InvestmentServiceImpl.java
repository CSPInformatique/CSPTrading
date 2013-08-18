package com.cspinformatique.csptrading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Investment;
import com.cspinformatique.csptrading.service.InvestmentService;
import com.cspinformatique.csptrading.service.QuoteService;

@Service
public class InvestmentServiceImpl implements InvestmentService {
	@Autowired private QuoteService quoteService;
	
	public List<Investment> getInvestments(){
		return null;
	}
	
	public List<Investment> getStockInvestments(){
		return null;
	}
	
	public List<Investment> getOpenInvestments(){
		return null;
	}
	
	public double calculateInvestment(Investment investment){
		return (
			(	investment.getSellOrder().getPrice() * 
				investment.getSellOrder().getQuantity()
			) - 
			investment.getSellOrder().getBrokerFees() - 
			investment.getBuyOrder().getBrokerFees()
		) - (	
			(	investment.getBuyOrder().getPrice() * 
				investment.getBuyOrder().getQuantity()
			)
		);
	}
	
	
}
