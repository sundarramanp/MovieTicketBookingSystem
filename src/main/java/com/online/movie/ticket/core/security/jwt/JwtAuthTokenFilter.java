package com.online.movie.ticket.core.security.jwt;
import org.springframework.web.util.NestedServletException;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

import com.online.movie.ticket.service.AuthUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private AuthUserDetailService userDetailsService;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
    								HttpServletResponse response, 
    								FilterChain filterChain) 
    										throws ServletException, IOException {
    
    	response.addHeader("JWT-Status", "");    
    	if (request.getRequestURI().indexOf("/auth/signin") != -1 || request.getRequestURI().indexOf("/user/save") != -1)
    	{
    		filterChain.doFilter(request, response);
    		return;
    	}
    	  
        try {
        
        	String jwt;
        	log.info(request.getRequestURI());
        	
        	jwt = getJwt(request);        
            if (jwt!=null && tokenProvider.validateJwtToken(jwt)) {
                String username = tokenProvider.getUserNameFromJwtToken(jwt);
                log.info(username);
                if (username != null)
                {               
                	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                	if (userDetails != null)
                	{
		                UsernamePasswordAuthenticationToken authentication 
		                		= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                SecurityContextHolder.getContext().setAuthentication(authentication);
		                response.addHeader("JWT-Status", "SUCCESS");
                	}
                }
            
            }
        }  
         catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e.getMessage());
            response.setHeader("JWT-Status", "Invalid Token Signature");
        } catch (MalformedJwtException e) {
        	log.error("Invalid JWT token -> Message: {}", e.getMessage());
        	 response.setHeader("JWT-Status", "Invalid Token");
        } catch (ExpiredJwtException e) {
        	log.error("Expired JWT token -> Message: {}", e.getMessage());
        	response.setHeader("JWT-Status", "Token Session Expired");
        } catch (UnsupportedJwtException e) {
        	log.error("Unsupported JWT token -> Message: {}", e.getMessage());
        	response.setHeader("JWT-Status", "Unsupported Token");
        } catch (IllegalArgumentException e) {
        	log.error("JWT claims string is empty -> Message: {}", e.getMessage());
        	response.setHeader("JWT-Status", "Token Claim statement is empty");
        } catch (Exception e) {
            log.error("Can not set user authentication -> Message: {}", e.getMessage());
            response.setHeader("JWT-Status", e.getMessage());
        }    
        filterChain.doFilter(request, response);
     
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        	
        if (authHeader != null && authHeader.startsWith("eSuiteToken ")) {
        	return authHeader.replace("eSuiteToken ","");
        }

        return null;
    }
}
