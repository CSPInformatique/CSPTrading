package com.cspinformatique.csptrading.repository.mongo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cspinformatique.csptrading.entity.Quote;

public interface QuoteRepository extends CrudRepository<Quote, String>, QuoteRepositoryCustom{
	public List<Quote> findByStockIdAndTimestampBetweenOrderByTimestampAsc(long stockId, Date fromDate, Date toDate);
}
