package com.online.movie.ticket.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.online.movie.ticket.service.StateMasterService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.model.StateMaster;
import lombok.extern.log4j.Log4j2;
/***
 * 
 * @author Sundar
 *
 */
@RestController
@Log4j2
@Validated 
@RequestMapping(value = "/api/v1/master/statemaster")
public class StateMasterController {
	@Autowired
	StateMasterService stateMasterService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public BaseDto create(@Valid @RequestBody StateMaster stateMaster) {
		log.debug("Create method is called..");
		return stateMasterService.create(stateMaster);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public BaseDto update(@Valid @RequestBody StateMaster stateMaster) {
		log.debug("Update method is called..[" + stateMaster.getId() + "]");
		return stateMasterService.update(stateMaster);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseDto delete(@PathVariable Long id) {
		log.debug("Delete method called....[" + id + "]");
		return stateMasterService.delete(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public BaseDto getByID(@Valid @PathVariable Long id) {
		log.debug("Create method is called.."+id);
		return stateMasterService.get(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public BaseDto get(){
		log.info("getall method called.");
		return stateMasterService.getAll();
	}
}