package com.cspinformatique.csptrading.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Wallet;

public interface PositionService {
	public double calculateReturnOnInvestment(Position position);
	
	public void closePosition(Position position, Date closeDate, double price);
	
	public Position getPosition(int id);
	
	public List<Position> getPositions();
	
	public List<Position> getClosedPositions(Wallet wallet);
	
	public List<Position> getOpenPositions(Wallet wallet);
	
	public Position savePosition(Position position);
}
