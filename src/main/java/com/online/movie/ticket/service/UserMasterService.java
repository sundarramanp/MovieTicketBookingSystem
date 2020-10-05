package com.online.movie.ticket.service;



import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.online.movie.ticket.core.dto.BaseDto;
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
public class UserMasterService  {
   
	@Autowired
	UserMasterRepository userMasterRepository;

	@Autowired
	UserMasterValidator userMasterValidator;
	
	
	@Transactional(readOnly=true)
	public BaseDto get(Long id) {

		log.debug("get method is called [" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			ValidateUtil.notNull(id, ErrorCode.MOVIE_ID_NOT_NULL);
			List<Long> results = new ArrayList<Long>();
			results.add(id);
			List <UserMaster> userMaster = userMasterRepository.findAllById(results);
		
			ValidateUtil.notNull(userMaster, ErrorCode.USER_MASTER_NOT_NULL);

			log.info("Successfully getting State by id.....[" +results+ "]");

			baseDto.setResponse(ErrorCode.SUCCESS);
			if (userMaster.size() >0)
			baseDto.setResponseObject(userMaster.get(0));
			else
				baseDto.setResponseObject(null);	

		} catch (RestException ex) {

			log.error("Exception in get method ", ex.getMessage());
			baseDto.setResponse(ex.getError());


		} catch (Exception e) {

			log.error("Exception in get method ", e);

			baseDto.setResponse(ErrorCode.FAILED);
		}

		return baseDto;
	}
	

	public BaseDto create(UserMaster userMaster) {
		log.info("Create method is called. dd....");

		BaseDto baseDto = new BaseDto();
		try {
			log.info("Create method is called. 1....");
			userMasterValidator.validate(userMaster);
			log.info("Create method is called. 2....");
			// Setting track time & user...
			userMaster.setSystemTrack(TimeUtil.getCreateSystemTrack());
			// Saving 
			log.info("Create method is called. 3....");
			UserMaster userMasterCreated = userMasterRepository.save(userMaster);
		
			log.info("User Master Saved successfully.....[" + userMasterCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(userMasterCreated);

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
	

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);
			System.out.println(exception.getMessage());
			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

		
			
			if (exceptionCause1.contains("UK_USER_MASTER_LOGIN_ID")) {
				baseDto.setResponse(ErrorCode.USER_MASTER_DETAIL_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	public BaseDto update(UserMaster existinguserMaster) {

		log.debug("update method is called....ID [ " + existinguserMaster.getId() + "]");

		BaseDto baseDto = new BaseDto();

		try {

			userMasterValidator.validate(existinguserMaster);

			
		
			List<Long> id = new ArrayList<Long>();
			id.add(existinguserMaster.getId());
			List <UserMaster> userMaster = userMasterRepository.findAllById(id);
			
			ValidateUtil.notNull(userMaster, ErrorCode.USER_MASTER_NOT_NULL);

		

			baseDto.setResponse(ErrorCode.SUCCESS);
			
			if (userMaster.size() >0)
			{
				existinguserMaster.setSystemTrack(userMaster.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existinguserMaster.getSystemTrack());
			}
			
			
			
			
			

			existinguserMaster = userMasterRepository.save(existinguserMaster);
			
			log.debug("User Master Updated successfully.....[" + existinguserMaster.getId() + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

			baseDto.setResponseObject(existinguserMaster);

		} catch (RestException exception) {

			log.error("Exception occured in update method ", exception.getMessage());
			baseDto.setResponse(exception.getError());
			

		} catch (ObjectOptimisticLockingFailureException e) {

			log.error("Error in editing StateMaster", e);

			baseDto.setResponse(ErrorCode.CANNOT_UPDATE_LOCKED_RECORD);
			
		} catch (JpaObjectRetrievalFailureException e) {
			
			log.error("Error in editing StateMaster", e);
			
			baseDto.setResponse(ErrorCode.CANNOT_UPDATE_DELETED_RECORD);
		
		}
		catch (Exception exception) {
			
			log.error("Exception occurred " + exception);

			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponse(ErrorCode.ERROR_GENERIC);

		
			if (exceptionCause1.contains("UK_USER_MASTER_LOGIN_ID")) {
				baseDto.setResponse(ErrorCode.USER_MASTER_DETAIL_ALREADY_EXIST);
			}
		}

		return baseDto;
	}
	

	public BaseDto delete(Long id) {

		log.debug("Delete method is Invoked...[" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			
			userMasterRepository.deleteById(id);
			log.info("User Master Deleted Successfully....[" + id + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

		} catch (DataIntegrityViolationException exception) {

			log.error("Exception occured : ", exception);

			ExceptionHelper helper = new ExceptionHelper(exception);

			log.error("Foreign key reference : " + helper.getFkReference());

			baseDto.setResponse(ErrorCode.ERROR_FK_CONSTRAINT);

		} catch (Exception exception) {

			log.error("Exception in Delete method : ", exception);

			baseDto.setResponse(ErrorCode.FAILED);
		}

		return baseDto;
	}
	public BaseDto getAll() {

		log.info("getAll method is called....");

		BaseDto baseDto = new BaseDto();

		try {

			List<UserMaster> userMasterList = userMasterRepository.findAll();

	
			
			log.info("Successfully getting list of institute...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(userMasterList);
			
		} catch (RestException re) {
			log.error("Exception in getAll method ", re.getMessage());
			baseDto.setResponse(re.getError());
			
		} catch (Exception e) {
			log.error("Exception in getAll method ", e);
			baseDto.setResponse(ErrorCode.FAILED);
			
		}
		return baseDto;
	}
}




