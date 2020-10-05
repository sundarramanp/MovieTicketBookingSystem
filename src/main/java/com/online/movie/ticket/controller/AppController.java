package com.online.movie.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.service.AppService;


import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping(value = "/api/v1/application")
public class AppController {

	@Autowired
	AppService appService;
	
	@RequestMapping(value = "/getallenums", method = RequestMethod.GET)
	public BaseDto getAllEnums() {
		log.debug("getAllEnums get all method called.");
		return appService.getAllEnums();
	}

}
