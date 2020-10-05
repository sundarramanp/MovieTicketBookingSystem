package com.online.movie.ticket.service;



import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.core.security.jwt.UserPrinciple;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.exception.ExceptionHelper;
import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.model.admin.UserMaster;
import com.online.movie.ticket.repository.UserMasterRepository;
import com.online.movie.ticket.util.TimeUtil;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.validation.UserMasterValidator;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class AuthUserDetailService implements UserDetailsService {
   
	@Autowired
	UserMasterRepository userMasterRepository;

	@Autowired
	UserMasterValidator userMasterValidator;
	
	@Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	log.debug("eSuit Custom loadUserByUsername Started "+username);
        UserMaster userDomain = userMasterRepository.findByUserID(username);
        if (userDomain == null ) {
        	 throw new UsernameNotFoundException("Invalid User");
        }
        return UserPrinciple.build(userDomain);

	
    }
	public static UserPrinciple getCurrentUser() {

		log.debug("GetCurrentUser Called");
		return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	
	

}




