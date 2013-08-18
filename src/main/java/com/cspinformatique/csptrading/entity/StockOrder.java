package com.cspinformatique.csptrading.entity;

public class StockOrder {
	private Stock stock;
	private double price;
	private double brokerFees;
	private int quantity;
	
	public StockOrder(){
		
	}
	
	public StockOrder(Stock stock, double price, double brokerFees, int quantity) {
		this.stock = stock;
		this.price = price;
		this.brokerFees = brokerFees;
		this.quantity = quantity;
	}
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getBrokerFees() {
		return brokerFees;
	}
	public void setBrokerFees(double brokerFees) {
		this.brokerFees = brokerFees;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
