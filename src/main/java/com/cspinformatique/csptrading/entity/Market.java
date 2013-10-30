package com.cspinformatique.csptrading.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Market implements Serializable {
	private static final long serialVersionUID = -6720215322297989970L;
	
	private String name;
	private int closingHour;
	private int closingMinute;
	private int openingHour;
	private int openingMinute;
	
	public Market() {
		
	}

	public Market(
		String name, 
		int closingHour, 
		int closingMinute,
		int openingHour, 
		int openingMinute
	){
		this.name = name;
		this.closingHour = closingHour;
		this.closingMinute = closingMinute;
		this.openingHour = openingHour;
		this.openingMinute = openingMinute;
	}

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClosingHour() {
		return closingHour;
	}

	public void setClosingHour(int closingHour) {
		this.closingHour = closingHour;
	}

	public int getClosingMinute() {
		return closingMinute;
	}

	public void setClosingMinute(int closingMinute) {
		this.closingMinute = closingMinute;
	}

	public int getOpeningHour() {
		return openingHour;
	}

	public void setOpeningHour(int openingHour) {
		this.openingHour = openingHour;
	}

	public int getOpeningMinute() {
		return openingMinute;
	}

	public void setOpeningMinute(int openingMinute) {
		this.openingMinute = openingMinute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + closingHour;
		result = prime * result + closingMinute;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + openingHour;
		result = prime * result + openingMinute;
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
		Market other = (Market) obj;
		if (closingHour != other.closingHour)
			return false;
		if (closingMinute != other.closingMinute)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openingHour != other.openingHour)
			return false;
		if (openingMinute != other.openingMinute)
			return false;
		return true;
	}
}
