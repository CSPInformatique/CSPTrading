package com.cspinformatique.csptrading.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.entity.Wallet;
import com.cspinformatique.csptrading.service.PositionService;
import com.cspinformatique.csptrading.service.StockService;
import com.cspinformatique.csptrading.service.WalletService;

@Controller
@RequestMapping("/wallet")
public class WalletController extends CspTradingController {
	@Autowired private PositionService positionService;
	@Autowired private StockService stockService;
	@Autowired private WalletService walletService;
	
	@Autowired private PositionController positionController;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(
		method=RequestMethod.POST, 
		params="close", 
		value="/{walletId}/position.json/{positionId}"
	)
	public void closeWalletPosition(
		@PathVariable int walletId, 
		@PathVariable int positionId, 
		@RequestParam long date, 
		@RequestParam double price
	){
		Position position = this.positionService.getPosition(positionId);
		position.setCloseDate(new Date(date));
		position.setSellOrder(
			new StockOrder(
				0, 
				position.getStock(), 
				price, 
				position.getBuyOrder().getBrokerFees(), 
				position.getBuyOrder().getQuantity()
			)
		);
		
		this.positionService.savePosition(position);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Wallet> getWallets(){
		return this.walletService.getWallets();
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String getWalletsPage(){
		return "wallet";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="/{wallet}/position", params="closed")
	public @ResponseBody List<Position> getWalletClosedPositions(@PathVariable Wallet wallet){
		return this.walletService.getWalletClosedPositions(wallet);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="/{wallet}/position", params="open")
	public @ResponseBody List<Position> getWalletOpenPositions(@PathVariable Wallet wallet){
		return this.walletService.getWalletOpenPositions(wallet);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.POST)
	public Wallet saveWallet(@RequestBody Wallet wallet){
		return this.walletService.saveWallet(wallet);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.POST, value="/{wallet}/position")
	public @ResponseBody Position addPositionToWallet(@RequestBody Position position){
		position.setStock(stockService.getStock(position.getStock().getSymbol()));
		position.getBuyOrder().setStock(position.getStock());
		
		return this.walletService.addPositionToWallet(
			this.walletService.getWallet(position.getWallet().getId()), 
			position
		);
	}
}
