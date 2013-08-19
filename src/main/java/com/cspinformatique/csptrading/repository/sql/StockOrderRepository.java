package com.cspinformatique.csptrading.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.StockOrder;

public interface StockOrderRepository extends JpaRepository<StockOrder, Integer>{

}
