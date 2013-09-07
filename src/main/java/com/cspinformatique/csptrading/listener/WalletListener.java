package com.cspinformatique.csptrading.listener;

import java.text.NumberFormat;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Wallet;

@Component
public class WalletListener implements EntityListener<Wallet> {
	@Autowired private NumberFormat numberFormat;
	
	@Override
	public void handlePostLoad(Wallet wallet) {
		try{
			wallet.setCurrentValue(wallet.getCurrentAmount());	
			
			int openPositions = 0;
			for(Position position : wallet.getPositions()){
				wallet.setCurrentValue(wallet.getCurrentValue() + position.getCurrentValue());
				if(position.getCloseDate() == null){
					++openPositions;
				}
			}
			
			wallet.setOpenPositions(openPositions);
			wallet.setPerformance(
				numberFormat.parse(numberFormat.format(
					((wallet.getCurrentValue() * 100) / wallet.getInitialAmount()) - 100
				)).doubleValue()
			);
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}

}
