package com.cspinformatique.csptrading.service;

import java.util.Date;

import com.cspinformatique.csptrading.entity.QuoteGap;

public interface QuoteGapService {
	public QuoteGap getQuoteGap(long stockId, Date date);
}
