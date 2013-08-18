package com.cspinformatique.csptrading.entity;

import java.util.Date;

public class Quote {
	private long stockId;
	private String symbol;
	private Date timestamp;
	
	private double open;
	private String time;
	private double pct;
	private double last;
	private long volume;
	private double high;
	private double ask;
	private double low;
	private double bid;
	private double prev;
	
	public Quote(){
		
	}

	public Quote(
		long stockId, 
		String symbol, 
		Date timestamp, 
		double open,
		String time, 
		double pct, 
		double last, 
		long volume, 
		double high,
		double ask, 
		double low, 
		double bid, 
		double prev
	) {
		this.stockId = stockId;
		this.symbol = symbol;
		this.timestamp = timestamp;
		this.open = open;
		this.time = time;
		this.pct = pct;
		this.last = last;
		this.volume = volume;
		this.high = high;
		this.ask = ask;
		this.low = low;
		this.bid = bid;
		this.prev = prev;
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPct() {
		return pct;
	}

	public void setPct(double pct) {
		this.pct = pct;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
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

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getPrev() {
		return prev;
	}

	public void setPrev(double prev) {
		this.prev = prev;
	}
}
