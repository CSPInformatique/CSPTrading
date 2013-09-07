package com.cspinformatique.csptrading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.entity.Wallet;
import com.cspinformatique.csptrading.exception.InsufficientFundsException;
import com.cspinformatique.csptrading.thread.QuotesProcessorThread;

public abstract class CspTradingController {
	private static final Logger logger = LoggerFactory.getLogger(QuotesProcessorThread.class);
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception ex) {
		logger.error("An internal server error occured.", ex);
		
		return "An internal server error occured.";
	}
	
	@ResponseBody
	@ExceptionHandler(InsufficientFundsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String handleException(InsufficientFundsException ex) {
		Wallet wallet = ex.getWallet();
		StockOrder buyOrder = ex.getPosition().getBuyOrder();
		
		return 
			"Insufficient funds on wallet " + 
			wallet.getName() + " (" + 
			wallet.getCurrentAmount() + " left, " + 
			buyOrder.getQuantity() * buyOrder.getPrice() + buyOrder.getBrokerFees() + 
			" expected).";
	}
}
