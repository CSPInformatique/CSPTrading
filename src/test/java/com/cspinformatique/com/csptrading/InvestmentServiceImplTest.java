package com.cspinformatique.com.csptrading;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cspinformatique.csptrading.entity.Investment;
import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.service.QuoteService;
import com.cspinformatique.csptrading.service.impl.InvestmentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentServiceImplTest {
	@Mock private QuoteService quoteService;
	
	@InjectMocks
	private InvestmentServiceImpl investmentServiceImpl;
	
	@Test
	public void calculateInvestmentTest(){
		Stock cspStock = new Stock(1, "CSP", "CSP Informatique", new Market("NASDAQ", 0, 0, 0, 0), null);
		
		Investment investment1 =	new Investment(
				cspStock, 
				new StockOrder(cspStock, 61.23d, 9.99d, 20),
				new StockOrder(cspStock, 62.58d, 9.99d, 20)
			);
		
		System.out.println(investmentServiceImpl.calculateInvestment(investment1));
		
		Investment investment =	new Investment(
									cspStock, 
									new StockOrder(cspStock, 19.64d, 9.99d, 100),
									new StockOrder(cspStock, 20.10d, 9.99d, 100)
								);		

		// (20.10 * 100 - 9.99 - 9.99) - 1964 = 16.02 
		Assert.assertEquals(
			26.02,
			investmentServiceImpl.calculateInvestment(investment),
			0.00001d
		);
	}
}
