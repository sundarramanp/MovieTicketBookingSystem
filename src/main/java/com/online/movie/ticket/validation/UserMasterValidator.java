package com.online.movie.ticket.validation;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.LoginUserType;
import com.online.movie.ticket.enumeration.UserStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.admin.UserMaster;
import com.online.movie.ticket.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserMasterValidator {
	
	public void validate(UserMaster userMaster) {

		
		log.debug("User Master Validation Begins .... ");
		
		ValidateUtil.notNull(userMaster,ErrorCode.USER_MASTER_NOT_NULL);
	
		// Validating User ID (Mandatory)
		ValidateUtil.notNullOrEmpty(userMaster.getUserID(),ErrorCode.USER_MASTER_USER_ID_NOT_NULL);
		userMaster.setUserID(userMaster.getUserID().trim().toUpperCase());
		ValidateUtil.checkPattern(userMaster.getUserID(), "UserID", true);
		
		// Validating User Password (Mandatory)
		ValidateUtil.notNullOrEmpty(userMaster.getPassword(),ErrorCode.USER_MASTER_PASSWORD_NOT_NULL);
		userMaster.setPassword(new BCryptPasswordEncoder().encode(userMaster.getPassword().trim().toUpperCase()));
		
		ValidateUtil.checkPattern(userMaster.getPassword(), "Password", true);
		
		// Validating user name (Mandatory)
		ValidateUtil.notNullOrEmpty(userMaster.getUserName(),ErrorCode.USER_MASTER_PASSWORD_NOT_NULL);
		userMaster.setUserName(userMaster.getUserName().trim().toUpperCase());
		ValidateUtil.checkPattern(userMaster.getUserName(), "UserName", true);
		
		// Validating User Type (Mandatory)
		ValidateUtil.validateEnum(LoginUserType.class, userMaster.getUserType(), ErrorCode.USER_MASTER_USER_TYPE_NOT_NULL);

		// Validating User Type (Mandatory)
		ValidateUtil.validateEnum(UserStatus.class, userMaster.getStatus(), ErrorCode.USER_MASTER_USER_STATUS_NOT_NULL);
				
		
		
		
		// Validating Description (Not Mandatory)
		
	   ValidateUtil.checkPattern(userMaster.getDescription(), "Description", true);

		

		log.debug("User Master Validation Ends .... ");
		
		
	}
	

}