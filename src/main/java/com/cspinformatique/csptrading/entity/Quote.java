package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Quote implements Serializable{
	private static final long serialVersionUID = -748203290803732969L;
	
	private ID _id;
	private long stockId;
	private Date timestamp;
	
	private String symbol;	
	private double open;
	private double close;
	private long volume;
	private double high;
	private double low;
	
	public Quote(){
		
	}

	public Quote(
		long stockId,
		Date timestamp,
		String symbol,
		double open,
		double close,
		long volume, 
		double high,
		double low
	) {
		this._id = new ID(stockId, timestamp);
		this.stockId = stockId;
		this.timestamp = timestamp;
		this.symbol = symbol;
		this.open = open;
		this.close = close;
		this.volume = volume;
		this.high = high;
		this.low = low;
	}

	public ID get_id() {
		return _id;
	}

	public void set_id(ID _id) {
		this._id = _id;
	}

	@Id
	public long getStockId() {
		return this.stockId;
	}
	
	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	
	@Id
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}
	
	public static class ID implements Serializable {
		private static final long serialVersionUID = 5611145650487836596L;
		
		private long stockId;
		private Date timestamp;
		
		public ID(){
			
		}
		
		public ID(long stockId, Date timestamp){
			this.stockId = stockId;
			this.timestamp = timestamp;
		}

		public long getStockId() {
			return stockId;
		}

		public void setStockId(long stockId) {
			this.stockId = stockId;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (stockId ^ (stockId >>> 32));
			result = prime * result
					+ ((timestamp == null) ? 0 : timestamp.hashCode());
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
			if (stockId != other.stockId)
				return false;
			if (timestamp == null) {
				if (other.timestamp != null)
					return false;
			} else if (!timestamp.equals(other.timestamp))
				return false;
			return true;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(close);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(high);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(low);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(open);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (stockId ^ (stockId >>> 32));
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + (int) (volume ^ (volume >>> 32));
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
		Quote other = (Quote) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (Double.doubleToLongBits(close) != Double
				.doubleToLongBits(other.close))
			return false;
		if (Double.doubleToLongBits(high) != Double
				.doubleToLongBits(other.high))
			return false;
		if (Double.doubleToLongBits(low) != Double.doubleToLongBits(other.low))
			return false;
		if (Double.doubleToLongBits(open) != Double
				.doubleToLongBits(other.open))
			return false;
		if (stockId != other.stockId)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}
}


