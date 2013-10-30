package com.cspinformatique.csptrading.repository.mongo.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.repository.mongo.QuoteRepositoryCustom;

public class QuoteRepositoryImpl implements QuoteRepositoryCustom{
	@Autowired MongoTemplate mongoTemplate;
	
	@Override
	public Quote findLastQuote(long stockId) {
		return mongoTemplate.findOne(
			new Query(
				Criteria.where("stockId").is(stockId))
					.with(new Sort(Direction.DESC, "id.timestamp"))
					.limit(1), 
			Quote.class
		);
	}
	
	@Override
	public double findAverageQuote(long stockId, Date fromDate, Date toDate){
		AggregationResults<Quote> result = this.mongoTemplate.aggregate(
	    	Aggregation.newAggregation(
	    		Aggregation.match(
					Criteria.where("stockId").is(stockId)
					.and("id.timestamp").gte(fromDate)
					.lte(toDate)
				),
				Aggregation.group("stockId").avg("last").as("last"),
				Aggregation.project().andInclude("last").andExclude("_id") 
			),
			"quote", 
			Quote.class
		);
		
	    if(result == null || result.getUniqueMappedResult() == null){
	    	return 0d;
	    }else{
	    	return result.getUniqueMappedResult().getClose();
	    }
	}
	
	@Override
	public Quote findLastQuoteBetweenDates(long stockId, Date fromDate, Date toDate) {
		
		return mongoTemplate.findOne(
			new Query(Criteria.where("stockId").is(stockId)).
					addCriteria(Criteria.where("timestamp").gt(fromDate).lt(toDate)).
				with(new Sort(Direction.DESC, "timestamp")).
				limit(1), 
			Quote.class
		);
	}
}
