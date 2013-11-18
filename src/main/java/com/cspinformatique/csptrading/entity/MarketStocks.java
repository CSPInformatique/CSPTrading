package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.List;

public class MarketStocks implements Serializable{
	private static final long serialVersionUID = -2690026546940552755L;
	
	private Market market;
	private List<String> stocks;
	
	public MarketStocks() {
		
	}
	
	public MarketStocks(Market market, List<String> stocks) {
		this.market = market;
		this.stocks = stocks;
	}
	
	public Market getMarket() {
		return market;
	}
	
	public void setMarket(Market market) {
		this.market = market;
	}
	
	public List<String> getStocks() {
		return stocks;
	}
	
	public void setStocks(List<String> stocks) {
		this.stocks = stocks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime * result + ((stocks == null) ? 0 : stocks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketStocks other = (MarketStocks) obj;
		if (market == null) {
			if (other.market != null)
				return false;
		} else if (!market.equals(other.market))
			return false;
		if (stocks == null) {
			if (other.stocks != null)
				return false;
		} else if (!stocks.equals(other.stocks))
			return false;
		return true;
	}
}
