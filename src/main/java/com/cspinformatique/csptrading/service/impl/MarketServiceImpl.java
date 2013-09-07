package com.cspinformatique.csptrading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.repository.sql.MarketRepository;
import com.cspinformatique.csptrading.service.MarketService;

@Service
public class MarketServiceImpl implements MarketService {
	@Autowired private MarketRepository marketRepository;
	
	@Override
	public List<Market> getMarkets() {
		return this.marketRepository.findAll();
	}

}
