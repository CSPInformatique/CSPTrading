package com.cspinformatique.csptrading.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StockOrder {
	private int id;
	private Stock stock;
	private double price;
	private double brokerFees;
	private int quantity;
	
	public StockOrder(){
		
	}
	
	public StockOrder(int id, Stock stock, double price, double brokerFees, int quantity) {
		this.id = id;
		this.stock = stock;
		this.price = price;
		this.brokerFees = brokerFees;
		this.quantity = quantity;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="stockId")
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
