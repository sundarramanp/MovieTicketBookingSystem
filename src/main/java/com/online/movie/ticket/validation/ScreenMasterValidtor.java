package com.online.movie.ticket.validation;



import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.ScreenMaster;
import com.online.movie.ticket.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ScreenMasterValidtor {
	
	public void validate(ScreenMaster screenMaster) {

		
		log.debug("Screen Master Validation Begins .... ");
		
		ValidateUtil.notNull(screenMaster,ErrorCode.SCREEN_NOT_NULL);
	
		// Validating TheaterName (Mandatory)
		ValidateUtil.notNullOrEmpty(screenMaster.getScreenName(),ErrorCode.SCREEN_NAME_NOT_NULL);
		screenMaster.setScreenName(screenMaster.getScreenName().trim().toUpperCase());
		ValidateUtil.checkPattern(screenMaster.getScreenName(), "screenName", true);
		
		ValidateUtil.notNull(screenMaster.getNumberOfRow(), ErrorCode.SCREEN_ROW_NOT_NULL);	
		
		ValidateUtil.notNull(screenMaster.getNumberOfColumn(), ErrorCode.SCREEN_COLUMN_NOT_NULL);
		// Validating Description (Not Mandatory)
		
		ValidateUtil.checkPattern(screenMaster.getRemark(), "screenDescription", true);

		// Validating Status (Mandatory)
		ValidateUtil.validateEnum(VisibilityStatus.class, screenMaster.getStatus(), ErrorCode.SCREEN_VISIBILITY_STATUS_NOTNULL);

		log.debug("Screen Master Validation Ends .... ");
		
		
	}
	

}