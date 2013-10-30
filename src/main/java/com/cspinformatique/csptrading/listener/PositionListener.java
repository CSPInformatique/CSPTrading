package com.cspinformatique.csptrading.listener;

import java.text.NumberFormat;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cspinformatique.csptrading.activetick.ActiveTickConnector;
import com.cspinformatique.csptrading.activetick.QuoteRequestor;
import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.service.PositionService;
import com.cspinformatique.csptrading.service.QuoteService;

@Component
public class PositionListener implements EntityListener<Position>{
	@Autowired private ActiveTickConnector activeTickConnector;
	@Autowired private PositionService positionService;
	@Autowired private QuoteService quoteService;
	
	@Autowired private NumberFormat numberFormat;
	
	@Override
	public void handlePostLoad(Position position) {
		try{
			// Retreives last price from Active Tick Web Service.
			Quote lastQuote = new Quote();
			lastQuote.setClose(new QuoteRequestor(position.getStock(), activeTickConnector.getSession()).requestQuote());
			position.setLastQuote(lastQuote);
			
			position.setOpenValue(
				this.numberFormat.parse(this.numberFormat.format(
						position.getBuyOrder().getPrice() * position.getBuyOrder().getQuantity()
				)).doubleValue()
			);
			
			if(position.getCloseDate() == null){
				position.setSellOrder(
					new StockOrder(
						0, 
						position.getStock(), 
						position.getLastQuote().getClose(), 
						position.getBuyOrder().getBrokerFees(), 
						position.getBuyOrder().getQuantity()
					)
				);
			}
			
			position.setCurrentValue(
				this.numberFormat.parse(this.numberFormat.format(
					position.getSellOrder().getPrice() * position.getBuyOrder().getQuantity()
				)).doubleValue()
			);
			
			position.setReturnOnInvestment(
				this.numberFormat.parse(this.numberFormat.format(
					positionService.calculateReturnOnInvestment(position)
				)).doubleValue()
			);
			
			position.setPerformance(
				this.numberFormat.parse(this.numberFormat.format(
					(((position.getOpenValue() + position.getReturnOnInvestment()) * 100) / position.getOpenValue()) - 100
				)).doubleValue()
			);
		}catch(ParseException parseEx){
			throw new RuntimeException(parseEx);
		}
	}
}
