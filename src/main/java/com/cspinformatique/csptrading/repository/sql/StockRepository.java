package com.cspinformatique.csptrading.repository.sql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cspinformatique.csptrading.entity.Market;
import com.cspinformatique.csptrading.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	@Query(
		"SELECT " +
		"	stock " +
		"FROM " +
		"	Stock stock " +
		"WHERE " +
		"	stock IN ( " +
		"		SELECT " +
		"			stockWithPosition " +
		"		FROM " +
		"			Position position " +
		"		JOIN " +
		"			position.stock stockWithPosition" +
		"		WHERE" +
		"			position.closeDate IS NULL" +
		"	)"
	)
	public List<Stock> findWithPositions();
	
	public List<Stock> findByMarket(Market market);
	
	public Stock findBySymbol(String symbol);
}
