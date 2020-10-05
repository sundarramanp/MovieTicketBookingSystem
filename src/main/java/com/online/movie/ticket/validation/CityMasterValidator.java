package com.online.movie.ticket.validation;



import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.CityMaster;
import com.online.movie.ticket.util.ValidateUtil;


import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CityMasterValidator {
	
	
	
	public void validate(CityMaster cityMaster) {

		
		log.debug("City Master Validation Begins .... ");
		
		ValidateUtil.notNull(cityMaster,ErrorCode.CITY_NOT_NULL);
	
		
		// Validating CityCode (Mandatory)
		ValidateUtil.notNullOrEmpty(cityMaster.getCityCode(), ErrorCode.CITY_CODE_NOT_NULL);
		cityMaster.setCityCode(cityMaster.getCityCode().trim().toUpperCase());
		ValidateUtil.checkPattern(cityMaster.getCityCode(), "cityCode", true);
		
		
		// Validating CityName (Mandatory)
		ValidateUtil.notNullOrEmpty(cityMaster.getCityName(),ErrorCode.CITY_NAME_NOT_NULL);
		cityMaster.setCityName(cityMaster.getCityName().trim().toUpperCase());
		ValidateUtil.checkPattern(cityMaster.getCityName(), "cityName", true);
		
		// Validating Status (Mandatory)
				ValidateUtil.validateEnum(VisibilityStatus.class, cityMaster.getStatus(), ErrorCode.CITY_VISIBILITY_STATUS_NOTNULL);
		// Validating State
				
		// Validating State ID (Mandatory)
		
		ValidateUtil.notNullOrEmpty(cityMaster.getStateMaster().getId().toString(), ErrorCode.STATE_NOT_FOUND);		
		log.debug("State ID Validated.");
		
		// Validating Remark (Not Mandatory)
		
	   ValidateUtil.checkPattern(cityMaster.getRemark(), "cityRemark", true);

		

		log.debug("City Master Validation Ends .... ");
		
		
	}
	

}