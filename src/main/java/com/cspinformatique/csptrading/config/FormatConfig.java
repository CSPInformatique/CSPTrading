package com.cspinformatique.csptrading.config;

import java.text.NumberFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatConfig {
	public @Bean NumberFormat numberFormat(){
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(3);
		numberFormat.setMaximumFractionDigits(3);
		
		return numberFormat;
	}
}
