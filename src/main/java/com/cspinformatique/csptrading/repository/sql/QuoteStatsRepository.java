package com.cspinformatique.csptrading.repository.sql;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.QuoteStats;

public interface QuoteStatsRepository extends JpaRepository<QuoteStats, Integer> {
	public QuoteStats findByStockIdAndDate(long stockId, Date date);
}
