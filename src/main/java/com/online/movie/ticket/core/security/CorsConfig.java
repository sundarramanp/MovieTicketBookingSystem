package com.online.movie.ticket.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 
 * @author Sundar
 *
 */
@Configuration
public class CorsConfig{
	private final long MAX_AGE_SECS = 3600;
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				/*
				 * registry.addMapping("/**") .allowedMethods("GET", "POST", "PUT", "DELETE")
				 * .allowedHeaders("*") .allowedOrigins("http://localhost:4200");
				 */
				
			     registry.addMapping("/**")
	                .allowedOrigins("*")
	                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
	                .maxAge(MAX_AGE_SECS);
				
			}
		};
	}
}