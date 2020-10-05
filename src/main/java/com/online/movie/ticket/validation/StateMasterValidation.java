package com.online.movie.ticket.validation;



import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.StateMaster;
import com.online.movie.ticket.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class StateMasterValidation {

	public void validate(StateMaster stateMaster) {

		
		log.debug("StateMaster Master Validation Begins .... ");
		log.info(ErrorCode.STATE_NOT_NULL);
		ValidateUtil.notNull(stateMaster,ErrorCode.STATE_NOT_NULL);
	
		
		// Validating StateCode (Mandatory)
		ValidateUtil.notNullOrEmpty(stateMaster.getStateCode(), ErrorCode.STATE_CODE_NOT_NULL);
		stateMaster.setStateCode(stateMaster.getStateCode().trim().toUpperCase());
		ValidateUtil.checkPattern(stateMaster.getStateCode(), "stateCode", true);
		
		
		// Validating StateName (Mandatory)
		ValidateUtil.notNullOrEmpty(stateMaster.getStateName(),ErrorCode.STATE_NAME_NOT_NULL);
		stateMaster.setStateName(stateMaster.getStateName().trim().toUpperCase());
		ValidateUtil.checkPattern(stateMaster.getStateName(), "stateName", true);
		
		
		
		// Validating Description (Not Mandatory)
		
	   ValidateUtil.checkPattern(stateMaster.getRemark(), "stateRemark", true);

		// Validating Status (Mandatory)
		ValidateUtil.validateEnum(VisibilityStatus.class, stateMaster.getStatus(), ErrorCode.STATE_VISIBILITY_STATUS_NOTNULL);

		log.debug("State Master Validation Ends .... ");
		
		
	}
	

}
