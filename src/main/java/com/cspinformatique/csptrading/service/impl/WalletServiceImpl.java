package com.cspinformatique.csptrading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.entity.Wallet;
import com.cspinformatique.csptrading.exception.InsufficientFundsException;
import com.cspinformatique.csptrading.repository.sql.WalletRepository;
import com.cspinformatique.csptrading.service.PositionService;
import com.cspinformatique.csptrading.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
	@Autowired private WalletRepository walletRepository;
	@Autowired private PositionService positionService;
	
	@Override
	public Position addPositionToWallet(Wallet wallet, Position position){
		// Adding references.
		position.setWallet(wallet);
		wallet.getPositions().add(position);
		
		// Current amount calculation.
		StockOrder buyOrder = position.getBuyOrder();
		double currentAmount =	wallet.getCurrentAmount() - (
									buyOrder.getBrokerFees() + (buyOrder.getPrice() * buyOrder.getQuantity())
								);
		
		if(currentAmount < 0){
			throw new InsufficientFundsException(wallet, position);
		}
		
		wallet.setCurrentAmount(currentAmount);
		
		this.saveWallet(wallet);
		
		return position;
	}
	
	@Override
	public Wallet getWallet(int id){
		return this.walletRepository.findOne(id);
	}
	
	@Override
	public List<Position> getWalletClosedPositions(Wallet wallet){
		return this.positionService.getClosedPositions(wallet);
	}
	
	@Override
	public List<Position> getWalletOpenPositions(Wallet wallet){
		return this.positionService.getOpenPositions(wallet);
	}
	
	@Override
	public List<Wallet> getWallets(){
		return this.walletRepository.findAll();
	}
	
	@Override
	public Wallet saveWallet(Wallet wallet) {
		if(wallet.getCreationDate() == null){
			wallet.setCreationDate(new Date());
			wallet.setCurrentAmount(wallet.getInitialAmount());
			wallet.setCurrentValue(wallet.getCurrentAmount());;
		}
		
		return this.walletRepository.save(wallet);
	}
}
