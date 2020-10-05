
package com.online.movie.ticket.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.movie.ticket.service.MovieSnapService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.model.MovieSnaps;
import lombok.extern.log4j.Log4j2;
/***
 * 
 * @author Sundar
 *
 */
@RestController
@Log4j2
@Validated 
@RequestMapping(value = "/api/v1/master/movie/upload/image")
public class ImageSnapController {
	@Autowired
	MovieSnapService movieSnapService;
	
	@RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	public BaseDto create( @RequestParam("imageFile") MultipartFile file,
						   @PathVariable Long id) {
		log.info("Create method is called..");
		return movieSnapService.create(file,id);
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value = "/getImageByMovieID/{id}", method = RequestMethod.GET)
	public BaseDto getImageByMovieID(@Valid @PathVariable Long id) {
		log.debug("Get impage by movie method is called.."+id);
		return movieSnapService.getImageByMovieID(id);
	}
	
}