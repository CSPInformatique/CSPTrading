package com.cspinformatique.csptrading.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StocksAnalysisResult {
	private int id;
	private StocksAnalysis stocksAnalysis;
	private Stock stock;
	
	private int cycles;
	private double cyclesGap;
	private double quoteAverage;
	private double currentValue;
	
	public StocksAnalysisResult(){
		
	}
	
	public StocksAnalysisResult(
		int id, 
		StocksAnalysis stocksAnalysis,
		Stock stock, 
		int cycles, 
		double cyclesGap,
		double quoteAverage,
		double currentValue
	){
		this.id = id;
		this.stocksAnalysis = stocksAnalysis;
		this.stock = stock;
		this.cycles = cycles;
		this.cyclesGap = cyclesGap;
		this.quoteAverage = quoteAverage;
		this.currentValue = currentValue;
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
	@JoinColumn(name="stockAnalysis")
	public StocksAnalysis getStocksAnalysis() {
		return stocksAnalysis;
	}
	
	public void setStocksAnalysis(StocksAnalysis stocksAnalysis) {
		this.stocksAnalysis = stocksAnalysis;
	}
	
	@ManyToOne
	@JoinColumn(name="stock")
	public Stock getStock() {
		return stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	public int getCycles() {
		return cycles;
	}
	
	public void setCycles(int cycles) {
		this.cycles = cycles;
	}
	
	public double getCyclesGap() {
		return cyclesGap;
	}
	
	public void setCyclesGap(double cyclesGap) {
		this.cyclesGap = cyclesGap;
	}

	public double getQuoteAverage() {
		return quoteAverage;
	}

	public void setQuoteAverage(double quoteAverage) {
		this.quoteAverage = quoteAverage;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
}
