package com.cspinformatique.csptrading.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.csptrading.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

}
