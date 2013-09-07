package com.cspinformatique.csptrading.service.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.repository.mongo.QuoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceImplTest {
	@Mock private QuoteRepository quoteRepository;
	@Spy private RestTemplate restTemplate = new RestTemplate();
	@Spy private ObjectMapper objectMapper = new ObjectMapper();
	
	@InjectMocks
	private QuoteServiceImpl quoteService = new QuoteServiceImpl(); 
	
	@Test
	public void retreiveStockQuoteTest(){
		Assert.assertNotNull(quoteService.loadLatestQuoteFromProvider(new Stock(360097430, "", "", null, new Date())));
	}
}