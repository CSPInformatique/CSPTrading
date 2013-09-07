package com.cspinformatique.csptrading.entity;

import java.util.List;

public class MarketStocks {
	private Market market;
	private List<Stock> stocks;
	
	public MarketStocks() {
		
	}
	
	public MarketStocks(Market market, List<Stock> stocks) {
		this.market = market;
		this.stocks = stocks;
	}
	
	public Market getMarket() {
		return market;
	}
	
	public void setMarket(Market market) {
		this.market = market;
	}
	
	public List<Stock> getStocks() {
		return stocks;
	}
	
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
}
