package com.cspinformatique.csptrading.service;

import java.util.Date;

import com.cspinformatique.csptrading.entity.QuoteGap;
import com.cspinformatique.csptrading.entity.Stock;

public interface QuoteGapService {
	public QuoteGap getQuoteGap(Stock stock, Date date);
}
