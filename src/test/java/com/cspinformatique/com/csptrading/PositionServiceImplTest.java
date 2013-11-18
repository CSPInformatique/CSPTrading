package com.cspinformatique.com.csptrading;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.impl.PositionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PositionServiceImplTest {
	@Mock private QuoteService quoteService;
	
	@InjectMocks
	private PositionServiceImpl positionServiceImpl;
	
	@Test
	public void calculateInvestmentTest(){
		Stock cspStock = new Stock(1, "CSP", "CSP Informatique", new Market("NASDAQ", 0, 0, 0, 0), null, null);
		
		Position investment1 =	new Position(
									0,
									null,
									cspStock, 
									new StockOrder(0, cspStock, 61.23d, 9.99d, 20),
									new StockOrder(0, cspStock, 62.58d, 9.99d, 20),
									null,
									null,
									null,
									0,
									0,
									0,
									0
								);
		
		System.out.println(positionServiceImpl.calculateReturnOnInvestment(investment1));
		
		Position position =	new Position(
									0,
									null,
									cspStock, 
									new StockOrder(0, cspStock, 19.64d, 9.99d, 100),
									new StockOrder(0, cspStock, 20.10d, 9.99d, 100),
									null,
									null,
									null,
									0,
									0,
									0,
									0
								);		

		// (20.10 * 100 - 9.99 - 9.99) - 1964 = 16.02 
		Assert.assertEquals(
			26.02,
			positionServiceImpl.calculateReturnOnInvestment(position),
			0.00001d
		);
	}
}
