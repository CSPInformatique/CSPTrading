package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stock implements Serializable{
	private static final long serialVersionUID = -5190889193323856777L;
	
	private long id;
	private String symbol;
	private String name;
	private Market market;
	private Date lastQuoteTimestamp;
	
	public Stock() {
	
	}
	
	public Stock(
		long id, 
		String symbol, 
		String name, 
		Market market,
		Date lastQuoteTimestamp,
		Quote lastQuote, 
		StockStats stockStats
	){
		this.id = id;
		this.symbol = symbol;
		this.name = name;
		this.market = market;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime
				* result
				+ ((lastQuoteTimestamp == null) ? 0 : lastQuoteTimestamp
						.hashCode());
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Stock other = (Stock) obj;
		if (id != other.id)
			return false;
		if (lastQuoteTimestamp == null) {
			if (other.lastQuoteTimestamp != null)
				return false;
		} else if (!lastQuoteTimestamp.equals(other.lastQuoteTimestamp))
			return false;
		if (market == null) {
			if (other.market != null)
				return false;
		} else if (!market.equals(other.market))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
}
