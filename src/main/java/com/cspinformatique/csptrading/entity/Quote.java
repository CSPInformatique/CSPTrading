package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable{
	private static final long serialVersionUID = -748203290803732969L;
	
	private long stockId;
	private Date timestamp;
	
	private String symbol;	
	private double open;
	private double close;
	private long volume;
	private double high;
	private double low;
	
	public Quote(){
		
	}

	public Quote(
		long stockId,
		Date timestamp,
		String symbol,
		double open,
		double close,
		long volume, 
		double high,
		double low
	) {
		this.stockId = stockId;
		this.timestamp = timestamp;
		this.symbol = symbol;
		this.open = open;
		this.close = close;
		this.volume = volume;
		this.high = high;
		this.low = low;
	}

	public long getStockId() {
		return this.stockId;
	}
	
	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}
}


