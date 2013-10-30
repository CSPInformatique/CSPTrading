package com.cspinformatique.csptrading.repository.sql;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.StockStats;

public interface StockStatsRepository extends JpaRepository<StockStats, StockStats.ID> {
	public List<StockStats> findByDate(Date date);
	
	public StockStats findByStockIdAndDate(long stockId, Date date);
}
