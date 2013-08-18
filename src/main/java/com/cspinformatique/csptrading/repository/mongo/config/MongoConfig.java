package com.cspinformatique.csptrading.repository.mongo.config;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:persistence/mongo.properties")
@EnableMongoRepositories(basePackages="com.cspinformatique.csptrading.repository.mongo")
public class MongoConfig {
	private static final String MONGO_HOST = "mongo.host";
    private static final String MONGO_PORT = "mongo.port";
    private static final String MONGO_DATABASE = "mongo.database";  
    
	@Resource private Environment env;
    
	public @Bean MongoDbFactory mongoDbFactory(){
		try{
			return new SimpleMongoDbFactory(
				new MongoClient(
					env.getRequiredProperty(MONGO_HOST), 
					Integer.valueOf(env.getRequiredProperty(MONGO_PORT))
				), 
				env.getRequiredProperty(MONGO_DATABASE)
			);
		}catch(UnknownHostException unknowHostEx){
			throw new RuntimeException(unknowHostEx);
		}
	}
	
	public @Bean MongoTemplate mongoTemplate(){
		return new MongoTemplate(mongoDbFactory());
	}
}
