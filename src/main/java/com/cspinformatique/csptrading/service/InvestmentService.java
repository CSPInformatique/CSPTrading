package com.cspinformatique.csptrading.service;

import java.util.List;

import com.cspinformatique.csptrading.entity.Investment;

public interface InvestmentService {
	public List<Investment> getInvestments();
	
	public List<Investment> getStockInvestments();
	
	public List<Investment> getOpenInvestments();
	
	public double calculateInvestment(Investment investment);
}
