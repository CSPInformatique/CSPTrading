package com.cspinformatique.csptrading.repository.sql;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.QuoteGap;

public interface QuoteGapRepository extends JpaRepository<QuoteGap, Integer> {
	public QuoteGap findByStockIdAndDate(long stockId, Date date);
}
