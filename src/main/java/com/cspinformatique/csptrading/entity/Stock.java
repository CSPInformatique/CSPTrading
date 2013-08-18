package com.cspinformatique.csptrading.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stock {
	private String symbol;
	private String name;
	private Market market;
	private Date lastQuoteTimestamp;
	private long id;
	
	public Stock() {
	
	}
	
	public Stock(long id, String symbol, String name, Market market, Date lastQuoteTimestamp) {
		this.id = id;
		this.symbol = symbol;
		this.name = name;
		this.market = market;
		this.lastQuoteTimestamp = lastQuoteTimestamp;
	}
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name="market", referencedColumnName="name")
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) {
		this.market = market;
	}
	public Date getLastQuoteTimestamp() {
		return lastQuoteTimestamp;
	}
	public void setLastQuoteTimestamp(Date lastQuoteTimestamp) {
		this.lastQuoteTimestamp = lastQuoteTimestamp;
	}
}
