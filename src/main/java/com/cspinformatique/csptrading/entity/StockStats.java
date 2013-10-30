package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cspinformatique.csptrading.entity.StockStats.ID;

@Entity
@IdClass(ID.class)
public class StockStats implements Serializable{
	private static final long serialVersionUID = 431633950022761151L;
	
	private Stock stock;
	private Date date;
	private double last;
	private double lowAverage;
	private double lowAverageVariation;
	
	public StockStats() {
		
	}

	public StockStats(
		Stock stock,
		Date date,
		double last,
		double lowAverage,
		double lowAverageVariation
	){
		this.stock = stock;
		this.date = date;
		this.last = last;
		this.lowAverage = lowAverage;
		this.lowAverageVariation = lowAverageVariation;
	}

	@Id
	@ManyToOne
	@JoinColumn(name="stockId")
	public Stock getStock() {
		return this.stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Id
	public Date getDate() {
		return this.date;
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
	
	@Embeddable
	public static class ID implements Serializable {
		private static final long serialVersionUID = 5611145650487836596L;
		
		private Stock stock;
		private Date date;
		
		public ID(){
			
		}
		
		public ID(Stock stock, Date date){
			this.stock = stock;
			this.date = date;
		}

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + ((stock == null) ? 0 : stock.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ID other = (ID) obj;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (stock == null) {
				if (other.stock != null)
					return false;
			} else if (!stock.equals(other.stock))
				return false;
			return true;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(last);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lowAverage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lowAverageVariation);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockStats other = (StockStats) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(last) != Double
				.doubleToLongBits(other.last))
			return false;
		if (Double.doubleToLongBits(lowAverage) != Double
				.doubleToLongBits(other.lowAverage))
			return false;
		if (Double.doubleToLongBits(lowAverageVariation) != Double
				.doubleToLongBits(other.lowAverageVariation))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
}
