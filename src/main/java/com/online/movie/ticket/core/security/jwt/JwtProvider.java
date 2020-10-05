package com.online.movie.ticket.core.security.jwt;


import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;



import java.util.Date;

import javax.servlet.ServletException;

@Log4j2
@Component
public class JwtProvider {


    @Value("${esuite.app.jwtSecret}")
    private String jwtSecret;

    @Value("${esuite.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
    	
    	UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public boolean validateJwtToken(String authToken) 
    {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw  e ;
        } catch (MalformedJwtException e) {
        	throw  e;
        } catch (ExpiredJwtException e) {
        	throw  e;
        } catch (UnsupportedJwtException e) {
        	throw  e;
        } catch (IllegalArgumentException e) {
        	throw  e;
        }
        
        	
        
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}