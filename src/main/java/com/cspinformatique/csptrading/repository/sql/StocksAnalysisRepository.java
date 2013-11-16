package com.cspinformatique.csptrading.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.StocksAnalysis;

public interface StocksAnalysisRepository extends JpaRepository<StocksAnalysis, Integer> {

}
