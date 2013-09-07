package com.cspinformatique.csptrading.entity;

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
public class Position {
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
	
	
}
