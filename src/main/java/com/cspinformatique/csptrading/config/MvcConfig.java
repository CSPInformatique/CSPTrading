package com.cspinformatique.csptrading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	 public @Bean DomainClassConverter<?> domainClassConverter(){
		 return new DomainClassConverter<FormattingConversionService>(
			this.mvcConversionService()
		);
	 }
	 
	public @Bean ObjectMapper objectMapper(){
		return new ObjectMapper();
	}
	
	public @Bean RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
