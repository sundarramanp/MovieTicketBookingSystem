package com.online.movie.ticket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.online.movie.ticket.core.dto.LoginDto;
import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.service.AuthUserDetailService;
import com.online.movie.ticket.core.dto.BaseDto;

import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.core.security.jwt.JwtProvider;
import com.online.movie.ticket.core.security.jwt.UserPrinciple;


import lombok.extern.log4j.Log4j2;

/***
 * 
 * @author Sundar
 *
 */
@Log4j2
@RestController
@RequestMapping("/api/v1/auth")
@Validated 
public class AuthUserDetailController {
	
	@Autowired
	AuthUserDetailService authUserDetailService;
	
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    JwtProvider jwtProvider;
   
	  @RequestMapping(value = "/signin", method = RequestMethod.POST)
	    public BaseDto authenticateUser( @Valid  @RequestBody LoginDto  loginRequest) {    
		  BaseDto respDto = new BaseDto();
		  respDto.setTrackId(null);
		  log.debug("signin method");
		  try
		  {
			/*
			 * ValidateUtil.notNull(loginRequest, ErrorCode.UNAUTHORIZED_USER);
			 * ValidateUtil.validateString(loginRequest.getUserID());
			 * ValidateUtil.validateString(loginRequest.getPassword());
			 */
			  	  
		        Authentication authentication = authManager.authenticate(
		                new UsernamePasswordAuthenticationToken(
		                        loginRequest.getUserID(),
		                        loginRequest.getPassword()
		                )
		        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtProvider.generateJwtToken(authentication);
	        respDto.setResponse(ErrorCode.SUCCESS);
	        respDto.setTrackId("");
	        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();
	        userPrinciple.setToken(jwt);
	        respDto.setResponseObject(userPrinciple);
	     
	        log.debug("Login success.");
		}  
		  catch (RestException restException) {
			
			respDto.setResponseCode(restException.getMessage());
			respDto.setResponseDescription("Mandatory Value Missing");
		} 
			catch (BadCredentialsException badlogin)
			{
				respDto.setResponse(ErrorCode.LOGIN_BAD_CREDENTIAL_ERROR);
				//respDto.setResponseDescription(badlogin.getMessage());
			}
		
		  catch (Exception e) {
			log.error("Exception ", e);
			respDto.setResponse(ErrorCode.ERROR_GENERIC);
			respDto.setResponseDescription(e.getMessage());
		}
		  return respDto;
	    } 
	  
		@RequestMapping(value = "/signout", method = RequestMethod.GET)
		public void logout()
		{
			log.info("Logout Method is Invoked");
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		
}


