package com.cspinformatique.csptrading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.StockOrder;
import com.cspinformatique.csptrading.repository.sql.StockOrderRepository;
import com.cspinformatique.csptrading.service.StockOrderService;

@Service
public class StockOrderServiceImpl implements StockOrderService {
	@Autowired private StockOrderRepository stockOrderRepository;
	
	@Override
	public StockOrder saveStockOrder(StockOrder stockOrder) {
		return this.stockOrderRepository.save(stockOrder);
	}
}
