package com.online.movie.ticket.auth.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.online.movie.ticket.exception.ErrorCode;



import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Anonymous access when not logged in, call class of resource that needs to be logged in-/
 */
@Log4j2
@Component
public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    	log.info("Login failed--> Indicating that the request requires authentication.");
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        // Here you can place any message you want	
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,ErrorCode.UNAUTHORIZED_USER.getDescription());
        
    }
}