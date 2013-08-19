package com.cspinformatique.csptrading.service;

import java.util.List;

import com.cspinformatique.csptrading.entity.Position;

public interface PositionService {	
	public double calculateInvestment(Position position);
	
	public List<Position> getPositions();
	
	public void savePosition(Position position);
}
