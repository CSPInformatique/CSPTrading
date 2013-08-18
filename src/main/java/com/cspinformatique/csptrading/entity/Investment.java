package com.cspinformatique.csptrading.entity;

public class Investment {
	private Stock stock;
	private StockOrder buyOrder;
	private StockOrder sellOrder;
	
	public Investment() {
		
	}
	
	public Investment(Stock stock, StockOrder buyOrder, StockOrder sellOrder) {
		this.stock = stock;
		this.buyOrder = buyOrder;
		this.sellOrder = sellOrder;
	}
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public StockOrder getBuyOrder() {
		return buyOrder;
	}
	public void setBuyOrder(StockOrder buyOrder) {
		this.buyOrder = buyOrder;
	}
	public StockOrder getSellOrder() {
		return sellOrder;
	}
	public void setSellOrder(StockOrder sellOrder) {
		this.sellOrder = sellOrder;
	}
}
