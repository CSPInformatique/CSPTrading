package com.cspinformatique.csptrading.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuoteGap {
	private int id;
	private long stockId;
	private Date date;
	private double gap;
	
	public QuoteGap(){
		
	}
	
	public QuoteGap(int id, long stockId, Date date, double gap) {
		this.id = id;
		this.stockId = stockId;
		this.date = date;
		this.gap = gap;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
