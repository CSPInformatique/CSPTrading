package com.cspinformatique.csptrading.repository.sql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Integer>{
	public List<Position> findByCloseDateIsNull();
}
