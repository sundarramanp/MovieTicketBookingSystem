package com.online.movie.ticket.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;


import com.online.movie.ticket.service.UserMasterService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.model.admin.UserMaster;

import com.online.movie.ticket.core.security.jwt.JwtProvider;



import lombok.extern.log4j.Log4j2;

/***
 * 
 * @author Sundar
 *
 */
@Log4j2
@RestController
@RequestMapping("/api/v1/user")
@Validated 
public class UserMasterController {
	
	@Autowired
	UserMasterService userMasterService;
	
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    JwtProvider jwtProvider;
   
		
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public BaseDto create(@Valid @RequestBody UserMaster userMaster) {
			log.debug("Create method is called..");
			return userMasterService.create(userMaster);
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public BaseDto update(@Valid @RequestBody UserMaster userMaster) {
			log.debug("Update method is called..[" + userMaster.getId() + "]");
			return userMasterService.update(userMaster);
		}

		@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
		public BaseDto delete(@PathVariable Long id) {
			log.debug("Delete method called....[" + id + "]");
			return userMasterService.delete(id);
		}
		@Transactional(readOnly=true)
		@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
		public BaseDto getByID(@Valid @PathVariable Long id) {
			log.debug("Create method is called.."+id);
			return userMasterService.get(id);
		}
		@Transactional(readOnly=true)
		@RequestMapping(value = "/getall", method = RequestMethod.GET)
		public BaseDto get(){
			log.debug("getall method called.");
			return userMasterService.getAll();
		}

}


