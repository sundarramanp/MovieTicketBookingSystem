package com.online.movie.ticket.validation;


import org.springframework.stereotype.Service;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.ScreenShow;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.enumeration.MovieShowStatus;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ScreenShowValidator {
	
	public void validate(ScreenShow screenShow) {

		
		log.debug("Screen Show Master Validation Begins .... ");
		
		ValidateUtil.notNull(screenShow,ErrorCode.SCREEN_SHOW_NOT_NULL);
	
		ValidateUtil.notNullOrEmpty(screenShow.getMovieMaster().getId().toString(), ErrorCode.SCREEN_SHOW_MOVIE_ID_NOT_NULL);	
		
		ValidateUtil.notNullOrEmpty(screenShow.getScreenMaster().getId().toString(), ErrorCode.SCREEN_SHOW_SCREEN_ID_NOT_NULL);	
		
		ValidateUtil.notNullOrEmpty(screenShow.getShowMaster().getId().toString(), ErrorCode.SCREEN_SHOW_SHOW_ID_NOT_NULL);	
		
		ValidateUtil.validateEnum(MovieShowStatus.class, screenShow.getScreenStatus(), ErrorCode.SCREEN_SHOW_STATUS_NOT_NULL);
		
		
		//Validate STart Date 
		ValidateUtil.notNull(screenShow.getStartDate(), ErrorCode.SCREEN_SHOW_START_DATE_NOT_NULL);	
		
		//Validate End Date 
		ValidateUtil.notNull(screenShow.getEndDate(), ErrorCode.SCREEN_SHOW_END_DATE_NOT_NULL);	

		if (screenShow.getEndDate().before(screenShow.getStartDate()))
		{
			ValidateUtil.logicalError(screenShow, ErrorCode.SCREEN_SHOW_DATE_VALID);
		}
		log.debug("Screen Show Master Validation Ends .... ");
		
		
	}
	

}