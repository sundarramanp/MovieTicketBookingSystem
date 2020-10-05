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
import com.online.movie.ticket.service.ScheduleScreenShowService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.model.ScheduleScreenShow;


import lombok.extern.log4j.Log4j2;
/***
 * 
 * @author Sundar
 *
 */
@RestController
@Log4j2
@Validated 
@RequestMapping(value = "/api/v1/master/ScheduleScreen")
public class ScheduleScreenShowController {
	@Autowired
	ScheduleScreenShowService scheduleScreenShowService;
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BaseDto create(@Valid @RequestBody ScheduleScreenShow scheduleScreenShow) {
		log.debug("Create method is called..");
		return scheduleScreenShowService.create(scheduleScreenShow);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public BaseDto update(@Valid @RequestBody ScheduleScreenShow scheduleScreenShow) {
		log.debug("Update method is called..[" + scheduleScreenShow.getId() + "]");
		return scheduleScreenShowService.update(scheduleScreenShow);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseDto delete(@PathVariable Long id) {
		log.debug("Delete method called....[" + id + "]");
		return scheduleScreenShowService.delete(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public BaseDto getByID(@Valid @PathVariable Long id) {
		log.debug("Create method is called.."+id);
		return scheduleScreenShowService.get(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public BaseDto get(){
		log.debug("getall method called.");
		return scheduleScreenShowService.getAll();
	}
	

	@RequestMapping(value = "/getAllShow", method = RequestMethod.GET)
	public BaseDto getAllShow(){
		log.debug("getAllShow method called.");
		return scheduleScreenShowService.getAllShow();
	}
	

	@RequestMapping(value = "/getBookingList", method = RequestMethod.GET)
	public BaseDto getBookingList(){
		log.debug("getBookingList method called.");
		return scheduleScreenShowService.getBookingList();
	}
}