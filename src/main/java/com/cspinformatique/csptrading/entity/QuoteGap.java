package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cspinformatique.csptrading.entity.QuoteGap.ID;

@Entity
@IdClass(ID.class)
public class QuoteGap implements Serializable{
	private static final long serialVersionUID = -1832310877516439827L;
	
	private Stock stock;
	private Date date;
	private double gap;
	
	public QuoteGap(){
		
	}
	
	public QuoteGap(Stock stock, Date date, double gap) {
		this.stock = stock;
		this.date = date;
		this.gap = gap;
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

	public double getGap() {
		return gap;
	}

	public void setGap(double gap) {
		this.gap = gap;
	}
	
	@Embeddable
	public static class ID implements Serializable {
		private static final long serialVersionUID = -161573412123349223L;
		
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
		temp = Double.doubleToLongBits(gap);
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
		QuoteGap other = (QuoteGap) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(gap) != Double.doubleToLongBits(other.gap))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
}
