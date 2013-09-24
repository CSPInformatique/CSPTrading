package com.cspinformatique.csptrading.repository.mongo;

import java.util.Date;

import com.cspinformatique.csptrading.entity.Quote;

public interface QuoteRepositoryCustom {
	public double findAverageQuote(long stockId, Date fromDate, Date toDate);
	
	public Quote findLastQuote(long stockId);
	
	public Quote findLastQuoteBetweenDates(long stockId, Date fromDate, Date toDate);
}
