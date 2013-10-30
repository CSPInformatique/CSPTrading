package com.cspinformatique.csptrading.activetick.config;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.cspinformatique.csptrading.activetick.ActiveTickConnector;
import com.cspinformatique.csptrading.activetick.ActiveTickProperties;

@Configuration
@PropertySource("classpath:activeTick.properties")
public class ActiveTickConfig {
	@Resource
    private Environment env;
	
	@Autowired ServletContext servletContext;
	
	public @Bean ActiveTickConnector activeTickConnector(){
		return new ActiveTickConnector(activeTickProperties());
	}
	
	public @Bean ActiveTickProperties activeTickProperties(){
		return new ActiveTickProperties(
		env.getRequiredProperty("com.cspinformatique.csptrading.activetick.server"),
		env.getRequiredProperty("com.cspinformatique.csptrading.activetick.port", Integer.class),
		servletContext.getInitParameter("com.cspinformatique.csptrading.activetick.apiKey"),
		servletContext.getInitParameter("com.cspinformatique.csptrading.activetick.user"),
		servletContext.getInitParameter("com.cspinformatique.csptrading.activetick.password"));
	}
}
