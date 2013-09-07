package com.cspinformatique.csptrading.repository.sql;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	public List<Stock> findByLastQuoteTimestampLessThanOrLastQuoteTimestampIsNull(Date lastQuoteTimestamp);
	
	public List<Stock> findByMarket(Market market);
	
	public Stock findBySymbol(String symbol);
}
