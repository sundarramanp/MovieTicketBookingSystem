package com.online.movie.ticket.validation;


import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.TheaterMaster;
import com.online.movie.ticket.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TheaterMasterValidator {
	
	public void validate(TheaterMaster theaterMaster) {

		
		log.debug("Theater Master Validation Begins .... ");
		
		ValidateUtil.notNull(theaterMaster,ErrorCode.THEATER_NOT_NULL);
	
		// Validating TheaterName (Mandatory)
		ValidateUtil.notNullOrEmpty(theaterMaster.getTheaterName(),ErrorCode.THEATER_NAME_NOT_NULL);
		theaterMaster.setTheaterName(theaterMaster.getTheaterName().trim().toUpperCase());
		ValidateUtil.checkPattern(theaterMaster.getTheaterName(), "theaterName", true);
		
		
		
		// Validating Description (Not Mandatory)
		
	   ValidateUtil.checkPattern(theaterMaster.getRemark(), "theaterDescription", true);

		// Validating Status (Mandatory)
		ValidateUtil.validateEnum(VisibilityStatus.class, theaterMaster.getStatus(), ErrorCode.THEATER_VISIBILITY_STATUS_NOTNULL);

		log.debug("Theater Master Validation Ends .... ");
		
		
	}
	

}