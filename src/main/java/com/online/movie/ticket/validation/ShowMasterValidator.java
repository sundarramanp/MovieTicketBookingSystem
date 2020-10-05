package com.online.movie.ticket.validation;


import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.ShowMaster;
import com.online.movie.ticket.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ShowMasterValidator {
	
	public void validate(ShowMaster showMaster) {

		
		log.debug("Show Master Validation Begins .... ");
		
		ValidateUtil.notNull(showMaster,ErrorCode.THEATER_NOT_NULL);
	
		// Validating TheaterName (Mandatory)
		ValidateUtil.notNullOrEmpty(showMaster.getShowName(),ErrorCode.SCREEN_NAME_NOT_NULL);
		showMaster.setShowName(showMaster.getShowName().trim().toUpperCase());
		ValidateUtil.checkPattern(showMaster.getShowName(), "showName", true);
		

		//Validate STart Date 
		ValidateUtil.notNull(showMaster.getShowStartTime(), ErrorCode.SHOW_START_TIME_NOT_NULL);	
		
		//Validate End Date 
		ValidateUtil.notNull(showMaster.getShowEndTime(), ErrorCode.SHOW_END_TIME_NOT_NULL);	
		
		log.debug("Show Master Validation Ends .... ");
		
		
	}
	

}