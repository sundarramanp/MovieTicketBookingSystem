
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
import com.online.movie.ticket.service.MovieMasterService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.model.MovieMaster;
import lombok.extern.log4j.Log4j2;
/***
 * 
 * @author Sundar
 *
 */
@RestController
@Log4j2
@Validated 
@RequestMapping(value = "/api/v1/master/movie")
public class MoiveMasterController {
	@Autowired
	MovieMasterService movieMasterService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public BaseDto create(@Valid @RequestBody MovieMaster movieMaster) {
		log.debug("Create method is called..");
		return movieMasterService.create(movieMaster);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public BaseDto update(@Valid @RequestBody MovieMaster movieMaster) {
		log.debug("Update method is called..[" + movieMaster.getId() + "]");
		return movieMasterService.update(movieMaster);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseDto delete(@PathVariable Long id) {
		log.debug("Delete method called....[" + id + "]");
		return movieMasterService.delete(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public BaseDto getByID(@Valid @PathVariable Long id) {
		log.debug("Create method is called.."+id);
		return movieMasterService.get(id);
	}
	@Transactional(readOnly=true)
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public BaseDto get(){
		log.debug("getall method called.");
		return movieMasterService.getAll();
	}
}