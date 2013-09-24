package com.cspinformatique.csptrading.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class QuoteStats {
	private int id;
	private Stock stock;
	private Date date;
	private double last;
	private double lowAverage;
	private double lowAverageVariation;
	
	public QuoteStats() {
		
	}

	public QuoteStats(
		int id, 
		Stock stock, 
		Date date, 
		double last,
		double lowAverage,
		double lowAverageVariation
	){
		this.id = id;
		this.stock = stock;
		this.date = date;
		this.last = last;
		this.lowAverage = lowAverage;
		this.lowAverageVariation = lowAverageVariation;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public double getLowAverage() {
		return lowAverage;
	}

	public void setLowAverage(double lowAverage) {
		this.lowAverage = lowAverage;
	}

	public double getLowAverageVariation() {
		return lowAverageVariation;
	}

	public void setLowAverageVariation(double lowAverageVariation) {
		this.lowAverageVariation = lowAverageVariation;
	}
}
