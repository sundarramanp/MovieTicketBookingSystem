package com.online.movie.ticket.validation;



import org.springframework.stereotype.Service;

import com.online.movie.ticket.enumeration.TicketStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.ScreenSeatBooking;
import com.online.movie.ticket.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ScreenSeatBookingValidator {
	
	public void validate(ScreenSeatBooking screenSeatBooking) {

		
		log.debug("screenSeatBooking Validation Begins .... ");
		
		ValidateUtil.notNull(screenSeatBooking,ErrorCode.SCREEN_SEAT_SHOW_NOT_NULL);
	
		// Validating User ID (Mandatory)
		ValidateUtil.notNull(screenSeatBooking.getScheduleScreenShow(),ErrorCode.SCHEDULE_SCREEN_SHOW_NOT_NULL);
		
	
		ValidateUtil.validateEnum(TicketStatus.class, screenSeatBooking.getStatus(), ErrorCode.SCREEN_SEAT_SHOW_STATUS_NOT_NULL);
		log.debug("screenSeatBooking Validation Ends .... ");
		
		
	}
	

}