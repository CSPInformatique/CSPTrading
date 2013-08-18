package com.cspinformatique.csptrading.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Market {
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
}
