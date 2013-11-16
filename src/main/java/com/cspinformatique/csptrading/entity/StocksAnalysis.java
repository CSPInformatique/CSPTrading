package com.cspinformatique.csptrading.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StocksAnalysis {
	private int id;
	private float cyclesMarginPercent;
	private long minimumVolume;
	private Date startDate;
	private Date endDate;
	private int stocksToProcess;
	private int stocksProcessed;
	
	public StocksAnalysis() {
		
	}

	public StocksAnalysis(
		int id, 
		float cyclesMarginPercent,
		long minimumVolume, 
		Date startDate, 
		Date endDate,
		int stocksToProcess,
		int stocksProcessed
	) {
		this.id = id;
		this.cyclesMarginPercent = cyclesMarginPercent;
		this.minimumVolume = minimumVolume;
		this.startDate = startDate;
		this.endDate = endDate;
		this.stocksToProcess = stocksToProcess;
		this.stocksProcessed = stocksProcessed;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCyclesMarginPercent() {
		return cyclesMarginPercent;
	}

	public void setCyclesMarginPercent(float cyclesMarginPercent) {
		this.cyclesMarginPercent = cyclesMarginPercent;
	}

	public long getMinimumVolume() {
		return minimumVolume;
	}

	public void setMinimumVolume(long minimumVolume) {
		this.minimumVolume = minimumVolume;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStocksToProcess() {
		return stocksToProcess;
	}

	public void setStocksToProcess(int stocksToProcess) {
		this.stocksToProcess = stocksToProcess;
	}

	public int getStocksProcessed() {
		return stocksProcessed;
	}

	public void setStocksProcessed(int stocksProcessed) {
		this.stocksProcessed = stocksProcessed;
	}
}
