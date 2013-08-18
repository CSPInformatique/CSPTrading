package com.cspinformatique.csptrading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {
	 public @Bean TilesViewResolver tilesViewResolver(){
		 return new TilesViewResolver();
	 }
	 
	 public @Bean TilesConfigurer tilesConfigurer(){
		 TilesConfigurer configurer = new TilesConfigurer();
		 
		 configurer.setDefinitions(new String[]{"WEB-INF/tiles.xml"});
		 
		 return configurer;
	 }
}
