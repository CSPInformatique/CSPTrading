package com.cspinformatique.csptrading.entity;

import java.io.Serializable;
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
public class Wallet implements Serializable{
	private static final long serialVersionUID = 6254076098300773577L;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(currentAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(currentValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(initialAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + openPositions;
		temp = Double.doubleToLongBits(performance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((positions == null) ? 0 : positions.hashCode());
		result = prime * result + (simulated ? 1231 : 1237);
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
		Wallet other = (Wallet) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (Double.doubleToLongBits(currentAmount) != Double
				.doubleToLongBits(other.currentAmount))
			return false;
		if (Double.doubleToLongBits(currentValue) != Double
				.doubleToLongBits(other.currentValue))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(initialAmount) != Double
				.doubleToLongBits(other.initialAmount))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openPositions != other.openPositions)
			return false;
		if (Double.doubleToLongBits(performance) != Double
				.doubleToLongBits(other.performance))
			return false;
		if (positions == null) {
			if (other.positions != null)
				return false;
		} else if (!positions.equals(other.positions))
			return false;
		if (simulated != other.simulated)
			return false;
		return true;
	}
	
}
