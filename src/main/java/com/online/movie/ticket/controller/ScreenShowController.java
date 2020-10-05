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
import com.online.movie.ticket.service.ScreenShowService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.model.ScreenShow;
import lombok.extern.log4j.Log4j2;
/***
 * 
 * @author Sundar
 *
 */
@RestController
@Log4j2
@Validated 
@RequestMapping(value = "/api/v1/master/ScreenShow")
public class ScreenShowController {
	@Autowired
	ScreenShowService screenShowService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public BaseDto create(@Valid @RequestBody ScreenShow screenShow) {
		log.debug("Create method is called..");
		return screenShowService.create(screenShow);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public BaseDto update(@Valid @RequestBody ScreenShow screenShow) {
		log.debug("Update method is called..[" + screenShow.getId() + "]");
		return screenShowService.update(screenShow);
	}
	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	public BaseDto schedule(@Valid @RequestBody ScreenShow screenShow) {
		log.debug("schedule method is called..");
	
		return screenShowService.schedule(screenShow);
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseDto delete(@PathVariable Long id) {
		log.debug("Delete method called....[" + id + "]");
		return screenShowService.delete(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public BaseDto getByID(@Valid @PathVariable Long id) {
		log.debug("Create method is called.."+id);
		return screenShowService.get(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public BaseDto get(){
		log.debug("getall method called.");
		return screenShowService.getAll();
	}
}