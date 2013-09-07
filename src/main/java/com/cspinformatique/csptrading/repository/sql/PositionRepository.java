package com.cspinformatique.csptrading.repository.sql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Wallet;

public interface PositionRepository extends JpaRepository<Position, Integer>{
	public List<Position> findByCloseDateIsNull();
	
	public List<Position> findByWalletAndCloseDateIsNotNull(Wallet wallet);
	
	public List<Position> findByWalletAndCloseDateIsNull(Wallet wallet);
}
