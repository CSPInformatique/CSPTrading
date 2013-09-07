package com.cspinformatique.csptrading.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Wallet {
	private int id;
	private String name;
	private boolean simulated;
	private Date creationDate;
	private double initialAmount;
	private double currentAmount;
	private double currentValue;
	private double performance;
	private int openPositions;
	
	@JsonManagedReference private List<Position> positions;
	
	public Wallet() {
		
	}
	
	public Wallet(
		int id,
		String name,
		List<Position> positions, 
		boolean simulated,
		Date creationDate,
		double initialAmount, 
		double currentAmount,
		double currentValue,
		double performance,
		int openPositions
	){
		this.id = id;
		this.name = name;
		this.positions = positions;
		this.simulated = simulated;
		this.creationDate = creationDate;
		this.initialAmount = initialAmount;
		this.currentAmount = currentAmount;
		this.currentValue = currentValue;
		this.performance = performance;
		this.openPositions = openPositions;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch=FetchType.EAGER, mappedBy="wallet", cascade=CascadeType.ALL)
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public boolean isSimulated() {
		return simulated;
	}

	public void setSimulated(boolean simulated) {
		this.simulated = simulated;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(double initialAmount) {
		this.initialAmount = initialAmount;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	@Transient
	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	@Transient
	public double getPerformance() {
		return performance;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}

	@Transient
	public int getOpenPositions() {
		return openPositions;
	}

	public void setOpenPositions(int openPositions) {
		this.openPositions = openPositions;
	}
	
}
