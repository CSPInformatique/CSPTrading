package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Stock implements Serializable{
	private static final long serialVersionUID = -5190889193323856777L;
	
	private long id;
	private String symbol;
	private String name;
	private Market market;
	private Quote lastQuote;
	private Date lastQuoteTimestamp;
	
	public Stock() {
	
	}
	
	public Stock(
		long id, 
		String symbol, 
		String name, 
		Market market,
		Quote lastQuote,
		Date lastQuoteTimestamp
	){
		this.id = id;
		this.symbol = symbol;
		this.name = name;
		this.market = market;
		this.lastQuote = lastQuote;
		this.lastQuoteTimestamp = lastQuoteTimestamp;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@JoinColumn(name="market")
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

	@Transient
	public Quote getLastQuote() {
		return lastQuote;
	}

	public void setLastQuote(Quote lastQuote) {
		this.lastQuote = lastQuote;
	}
}
