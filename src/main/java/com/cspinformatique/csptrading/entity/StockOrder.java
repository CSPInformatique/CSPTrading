package com.cspinformatique.csptrading.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StockOrder implements Serializable {
	private static final long serialVersionUID = 4272190878048444649L;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(brokerFees);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
		StockOrder other = (StockOrder) obj;
		if (Double.doubleToLongBits(brokerFees) != Double
				.doubleToLongBits(other.brokerFees))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
}
