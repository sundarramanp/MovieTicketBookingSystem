

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
import com.online.movie.ticket.service.ScreenSeatBookingService;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.core.dto.ReserveSeatDTO;
import com.online.movie.ticket.model.MovieMaster;
import com.online.movie.ticket.model.ScreenSeatBooking;


import lombok.extern.log4j.Log4j2;
/***
 * 
 * @author Sundar
 *
 */
@RestController
@Log4j2
@Validated 
@RequestMapping(value = "/api/v1/master/seatbooking")
public class ScreenSeatBookingController {
	@Autowired
	ScreenSeatBookingService screenSeatBookingService;
	
	@RequestMapping(value = "/getAllSeatByShow/{id}", method = RequestMethod.GET)
	public BaseDto getAllSeatByShow(@Valid @PathVariable Long id){
		log.debug("getAllShow method called.");
		return screenSeatBookingService.getByScheduleScreenID(id);
	}
	@Transactional
	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public BaseDto reserve( @RequestBody ReserveSeatDTO reserveSeatDTO) {
		log.debug("Reserve method is called..");
		return screenSeatBookingService.reserveSeat(reserveSeatDTO);
	}  

}