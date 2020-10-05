package com.online.movie.ticket.core.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


import lombok.extern.log4j.Log4j2;
@Configuration
@Log4j2
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    	log.error("Unauthorized error. Message - {}", authException.getMessage());
		/*
		 * response.setContentType("application/json;charset=UTF-8");
		 * response.addHeader("WWW-Authenticate", "***eSuite***"
		 * +authException.getMessage());
		 */
    	String tokenStatus =  response.getHeader("JWT-Status");
    	if (tokenStatus.equals("SUCCESS") || tokenStatus.equals(""))
    	{
    		log.debug(authException.getMessage());
    		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    	}
    	else
    	{
    		log.debug(HttpServletResponse.SC_FORBIDDEN+" "+tokenStatus);
    		response.sendError(HttpServletResponse.SC_FORBIDDEN,tokenStatus);
    	}
    
        
    }

}
