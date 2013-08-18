package com.cspinformatique.csptrading.entity;

import java.util.Date;

public class QuoteGap {
	private long stockId;
	private Date date;
	private double gap;
	
	public QuoteGap(){
		
	}
	
	public QuoteGap(long stockId, Date date, double gap) {
		this.stockId = stockId;
		this.date = date;
		this.gap = gap;
	}
	
	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getGap() {
		return gap;
	}

	public void setGap(double gap) {
		this.gap = gap;
	}
}
