package com.cspinformatique.csptrading.service;

import java.util.List;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Wallet;

public interface WalletService {
	public Position addPositionToWallet(Wallet wallet, Position position);
	
	public Wallet getWallet(int id);
	
	public List<Position> getWalletClosedPositions(Wallet wallet);
	
	public List<Position> getWalletOpenPositions(Wallet wallet);
	
	public List<Wallet> getWallets();
	
	public Wallet saveWallet(Wallet wallet);
}
