package com.cspinformatique.csptrading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Wallet;
import com.cspinformatique.csptrading.repository.sql.PositionRepository;
import com.cspinformatique.csptrading.service.PositionService;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.StockOrderService;
import com.cspinformatique.csptrading.service.WalletService;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {
	@Autowired private PositionRepository positionRepository;
	
	@Autowired private QuoteService quoteService;
	@Autowired private StockOrderService stockOrderService;
	@Autowired private WalletService walletService;
	
	@Override
	public double calculateReturnOnInvestment(Position position){
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
	
	@Override
	public List<Position> getClosedPositions(Wallet wallet){		
		return this.positionRepository.findByWalletAndCloseDateIsNotNull(wallet);
	}
	
	@Override
	public List<Position> getOpenPositions(Wallet wallet){		
		return this.positionRepository.findByWalletAndCloseDateIsNull(wallet);
	}
	
	@Override
	public Position getPosition(int id){
		return this.positionRepository.findOne(id);
	}
	
	@Override
	public List<Position> getPositions(){
		return this.positionRepository.findAll();
	}
	
	@Override
	public Position savePosition(Position position){		
		return this.positionRepository.save(position);
	}
}
