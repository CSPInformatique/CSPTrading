package com.cspinformatique.csptrading.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.Market;

public interface MarketRepository extends JpaRepository<Market, String> {
	
}
