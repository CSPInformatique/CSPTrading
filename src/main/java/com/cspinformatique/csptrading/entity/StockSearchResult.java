package com.cspinformatique.csptrading.entity;

import java.util.List;

public class StockSearchResult {
	private long results;
	private List<Stock> stocks;
	private int pageIndex;
	private int totalPages;
	
	public StockSearchResult(){
		
	}
	
	public StockSearchResult(long results, List<Stock> stocks, int pageIndex, int totalPages) {
		this.results = results;
		this.stocks = stocks;
		this.pageIndex = pageIndex;
		this.totalPages = totalPages;
	}
	
	public long getResults() {
		return results;
	}
	public void setResults(long results) {
		this.results = results;
	}
	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
