package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Position implements Serializable{
	private static final long serialVersionUID = 944054010974704021L;
	
	private int id;
	private Stock stock;
	private StockOrder buyOrder;
	private StockOrder sellOrder;
	private Date openDate;
	private Date closeDate;
	private Quote lastQuote;
	private double openValue;
	private double currentValue;
	private double returnOnInvestment;
	private double performance;
	
	@JsonBackReference private Wallet wallet;
	
	public Position() {
		
	}
	
	public Position(
		int id, 
		Wallet wallet, 
		Stock stock, 
		StockOrder buyOrder, 
		StockOrder sellOrder, 
		Date openDate, 
		Date closeDate,
		Quote lastQuote,
		double openValue,
		double currentValue,
		double returnOnInvestment,
		double performance
	) {
		this.id = id;
		this.wallet = wallet;
		this.stock = stock;
		this.buyOrder = buyOrder;
		this.sellOrder = sellOrder;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.lastQuote = lastQuote;
		this.openValue = openValue;
		this.currentValue = currentValue;
		this.returnOnInvestment = returnOnInvestment;
		this.performance = performance;
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
	@JoinColumn(name="walletId")
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@ManyToOne
	@JoinColumn(name="stockId")
	public Stock getStock() {
		return stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="buyOrderId")
	public StockOrder getBuyOrder() {
		return buyOrder;
	}
	
	public void setBuyOrder(StockOrder buyOrder) {
		this.buyOrder = buyOrder;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sellOrderId")
	public StockOrder getSellOrder() {
		return sellOrder;
	}
	
	public void setSellOrder(StockOrder sellOrder) {
		this.sellOrder = sellOrder;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public Date getCloseDate() {
		return closeDate;
	}
	
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Transient
	public Quote getLastQuote() {
		return lastQuote;
	}

	public void setLastQuote(Quote lastQuote) {
		this.lastQuote = lastQuote;
	}

	@Transient
	public double getOpenValue() {
		return openValue;
	}

	public void setOpenValue(double openValue) {
		this.openValue = openValue;
	}

	@Transient
	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	@Transient
	public double getReturnOnInvestment() {
		return returnOnInvestment;
	}

	public void setReturnOnInvestment(double returnOnInvestment) {
		this.returnOnInvestment = returnOnInvestment;
	}

	@Transient
	public double getPerformance() {
		return performance;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buyOrder == null) ? 0 : buyOrder.hashCode());
		result = prime * result
				+ ((closeDate == null) ? 0 : closeDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(currentValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result
				+ ((lastQuote == null) ? 0 : lastQuote.hashCode());
		result = prime * result
				+ ((openDate == null) ? 0 : openDate.hashCode());
		temp = Double.doubleToLongBits(openValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(performance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(returnOnInvestment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((sellOrder == null) ? 0 : sellOrder.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((wallet == null) ? 0 : wallet.hashCode());
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
		Position other = (Position) obj;
		if (buyOrder == null) {
			if (other.buyOrder != null)
				return false;
		} else if (!buyOrder.equals(other.buyOrder))
			return false;
		if (closeDate == null) {
			if (other.closeDate != null)
				return false;
		} else if (!closeDate.equals(other.closeDate))
			return false;
		if (Double.doubleToLongBits(currentValue) != Double
				.doubleToLongBits(other.currentValue))
			return false;
		if (id != other.id)
			return false;
		if (lastQuote == null) {
			if (other.lastQuote != null)
				return false;
		} else if (!lastQuote.equals(other.lastQuote))
			return false;
		if (openDate == null) {
			if (other.openDate != null)
				return false;
		} else if (!openDate.equals(other.openDate))
			return false;
		if (Double.doubleToLongBits(openValue) != Double
				.doubleToLongBits(other.openValue))
			return false;
		if (Double.doubleToLongBits(performance) != Double
				.doubleToLongBits(other.performance))
			return false;
		if (Double.doubleToLongBits(returnOnInvestment) != Double
				.doubleToLongBits(other.returnOnInvestment))
			return false;
		if (sellOrder == null) {
			if (other.sellOrder != null)
				return false;
		} else if (!sellOrder.equals(other.sellOrder))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (wallet == null) {
			if (other.wallet != null)
				return false;
		} else if (!wallet.equals(other.wallet))
			return false;
		return true;
	}
	
	
}
