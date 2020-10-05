package com.online.movie.ticket.validation;



import org.springframework.stereotype.Service;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.ScheduleScreenShow;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.enumeration.ScheduleStatus;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ScheduleScreenShowValidator {
	
	public void validate(ScheduleScreenShow scheduleScreenShow) {

		
		log.debug("Schedule Screen Show Validation Begins .... ");
		
		ValidateUtil.notNull(scheduleScreenShow,ErrorCode.SCHEDULE_SCREEN_SHOW_NOT_FOUND);
	
		ValidateUtil.notNullOrEmpty(scheduleScreenShow.getScreenShow().getId().toString(), ErrorCode.SCHEDULE_SCREEN_SHOW_NOT_NULL);	
	
		ValidateUtil.validateEnum(ScheduleStatus.class, scheduleScreenShow.getScheduleStatus(), ErrorCode.SCHEDULE_SCREEN_SHOW_DATE_NOT_NULL);
		
		
		//Validate schedule Date 
		ValidateUtil.notNull(scheduleScreenShow.getScreenDate(), ErrorCode.SCHEDULE_SCREEN_SHOW_DATE_NOT_NULL);	
		
	

		log.debug("Schedule Screen Show Validation Ends .... ");
		
		
	}
	

}