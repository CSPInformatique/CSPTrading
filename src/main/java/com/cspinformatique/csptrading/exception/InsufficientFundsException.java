package com.cspinformatique.csptrading.exception;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Wallet;

public class InsufficientFundsException extends RuntimeException {
	private static final long serialVersionUID = 6086428510343683609L;
	
	private Wallet wallet;
	private Position position;
	
	public InsufficientFundsException(Wallet wallet, Position position){
		this.wallet = wallet;
		this.position = position;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public Position getPosition() {
		return position;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
