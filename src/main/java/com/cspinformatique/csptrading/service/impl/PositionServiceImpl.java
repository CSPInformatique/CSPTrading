package com.cspinformatique.csptrading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.repository.sql.PositionRepository;
import com.cspinformatique.csptrading.service.PositionService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockOrderService;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {
	@Autowired private PositionRepository positionRepository;
	
	@Autowired private QuoteService quoteService;
	@Autowired private StockOrderService stockOrderService;
	
	public double calculateInvestment(Position position){
		return (
			(	position.getSellOrder().getPrice() * 
				position.getSellOrder().getQuantity()
			) - 
			position.getSellOrder().getBrokerFees() - 
			position.getBuyOrder().getBrokerFees()
		) - (	
			(	position.getBuyOrder().getPrice() * 
				position.getBuyOrder().getQuantity()
			)
		);
	}
	
	public List<Position> getPositions(){
		return this.positionRepository.findAll();
	}
	
	public void savePosition(Position position){
		this.positionRepository.save(position);
	}
}
