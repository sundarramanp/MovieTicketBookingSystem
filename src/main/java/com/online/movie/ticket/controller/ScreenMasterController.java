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
import com.online.movie.ticket.service.ScreenMasterService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.model.ScreenMaster;
import lombok.extern.log4j.Log4j2;
/***
 * 
 * @author Sundar
 *
 */
@RestController
@Log4j2
@Validated 
@RequestMapping(value = "/api/v1/master/screen")
public class ScreenMasterController {
	@Autowired
	ScreenMasterService screenMasterService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public BaseDto create(@Valid @RequestBody ScreenMaster screenMaster) {
		log.debug("Create method is called..");
		return screenMasterService.create(screenMaster);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public BaseDto update(@Valid @RequestBody ScreenMaster screenMaster) {
		log.debug("Update method is called..[" + screenMaster.getId() + "]");
		return screenMasterService.update(screenMaster);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseDto delete(@PathVariable Long id) {
		log.debug("Delete method called....[" + id + "]");
		return screenMasterService.delete(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public BaseDto getByID(@Valid @PathVariable Long id) {
		log.debug("Create method is called.."+id);
		return screenMasterService.get(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public BaseDto get(){
		log.debug("getall method called.");
		return screenMasterService.getAll();
	}
}