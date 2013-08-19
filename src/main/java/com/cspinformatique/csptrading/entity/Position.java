package com.cspinformatique.csptrading.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Position {
	private int id;
	private Stock stock;
	private StockOrder buyOrder;
	private StockOrder sellOrder;
	private Date openDate;
	private Date closeDate;
	
	public Position() {
		
	}
	
	public Position(int id, Stock stock, StockOrder buyOrder, StockOrder sellOrder, Date openDate, Date closeDate) {
		this.id = id;
		this.stock = stock;
		this.buyOrder = buyOrder;
		this.sellOrder = sellOrder;
		this.openDate = openDate;
		this.closeDate = closeDate;
	}
	
	@Id
	@GeneratedValue
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
	
	@OneToOne
	@JoinColumn(name="buyOrderId")
	public StockOrder getBuyOrder() {
		return buyOrder;
	}
	
	public void setBuyOrder(StockOrder buyOrder) {
		this.buyOrder = buyOrder;
	}
	
	@OneToOne
	@JoinColumn(name="sellOrderId")
	public StockOrder getSellOrder() {
		return sellOrder;
	}
	
	public void setSellOrder(StockOrder sellOrder) {
		this.sellOrder = sellOrder;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public Date getCloseDate() {
		return closeDate;
	}
	
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
}
