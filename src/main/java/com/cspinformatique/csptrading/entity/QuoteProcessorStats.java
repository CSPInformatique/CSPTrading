package com.cspinformatique.csptrading.entity;

import java.util.Date;

public class QuoteProcessorStats {
	private Date startTimestamp;
	private Date endTimestamp;
	private int progress;
	private boolean running;
	private int stocksProccessed;
	private int stocksToProccess;
	
	public QuoteProcessorStats(){
		
	}
	
	public QuoteProcessorStats(
		Date startTimestamp,
		Date endTimestamp,
		int progress,
		boolean running,
		int stocksProccessed, 
		int stocksToProccess
	) {
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
		this.progress = progress;
		this.running = running;
		this.stocksProccessed = stocksProccessed;
		this.stocksToProccess = stocksToProccess;
	}
	
	public Date getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public int getStocksProccessed() {
		return stocksProccessed;
	}
	public void setStocksProccessed(int stocksProccessed) {
		this.stocksProccessed = stocksProccessed;
	}
	public int getStocksToProccess() {
		return stocksToProccess;
	}
	public void setStocksToProccess(int stocksToProccess) {
		this.stocksToProccess = stocksToProccess;
	}

	public Date getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
}
