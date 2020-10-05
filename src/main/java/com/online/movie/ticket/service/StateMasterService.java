package com.online.movie.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import com.online.movie.ticket.repository.StateMasterRepository;
import com.online.movie.ticket.validation.StateMasterValidation;

import org.springframework.dao.DataIntegrityViolationException;
import com.online.movie.ticket.exception.ExceptionHelper;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.util.TimeUtil;
import com.online.movie.ticket.exception.ErrorCode;

import com.online.movie.ticket.model.StateMaster;
import com.online.movie.ticket.core.dto.BaseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Sundar
 *
 */
@Service
@Log4j2
public class StateMasterService {

	@Autowired 
	StateMasterRepository stateMasterRepository;
	
	@Autowired
	StateMasterValidation stateMasterValidation;
	
	@Transactional(readOnly=true)
	public BaseDto get(Long id) {

		log.debug("get method is called [" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			ValidateUtil.notNull(id, ErrorCode.STATE_ID_NOT_NULL);
			List<Long> results = new ArrayList<Long>();
			results.add(id);
			List <StateMaster> stateMaster = stateMasterRepository.findAllById(results);
		
			ValidateUtil.notNull(stateMaster, ErrorCode.STATE_ID_NOT_FOUND);

			log.info("Successfully getting State by id.....[" +results+ "]");

			baseDto.setResponse(ErrorCode.SUCCESS);
			if (stateMaster.size() >0)
			baseDto.setResponseObject(stateMaster.get(0));
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
	

	public BaseDto create(StateMaster stateMaster) {
		log.debug("Create method is called.....");

		BaseDto baseDto = new BaseDto();
		try {
			
			stateMasterValidation.validate(stateMaster);
			
			// Setting track time & user...
			stateMaster.setSystemTrack(TimeUtil.getCreateSystemTrack());
			// Saving 
			StateMaster stateMasterCreated = stateMasterRepository.save(stateMaster);
		
			log.debug("State Master Saved successfully.....[" + stateMasterCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(stateMasterCreated);

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
	

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

			if (exceptionCause1.contains("UK_STATE_MASTER_CODE")) {
				baseDto.setResponse(ErrorCode.STATE_CODE_ALREADY_EXIST);
			} 
			
			if (exceptionCause1.contains("UK_STATE_MASTER_NAME")) {
				baseDto.setResponse(ErrorCode.STATE_NAME_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	public BaseDto update(StateMaster existingStateMaster) {

		log.debug("update method is called....ID [ " + existingStateMaster.getId() + "]");

		BaseDto baseDto = new BaseDto();

		try {

			stateMasterValidation.validate(existingStateMaster);

			
		
			List<Long> id = new ArrayList<Long>();
			id.add(existingStateMaster.getId());
			List <StateMaster> stateMaster = stateMasterRepository.findAllById(id);
			
			ValidateUtil.notNull(stateMaster, ErrorCode.STATE_ID_NOT_FOUND);

		

			baseDto.setResponse(ErrorCode.SUCCESS);
			
			if (stateMaster.size() >0)
			{
				existingStateMaster.setSystemTrack(stateMaster.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existingStateMaster.getSystemTrack());
			}
			
			
			
			
			

			existingStateMaster = stateMasterRepository.save(existingStateMaster);
			
			log.debug("State Master Updated successfully.....[" + existingStateMaster.getId() + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

			baseDto.setResponseObject(existingStateMaster);

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

			if (exceptionCause1.contains("UK_STATE_MASTER_CODE")) {
				baseDto.setResponse(ErrorCode.STATE_CODE_ALREADY_EXIST);
			} 
			
			if (exceptionCause1.contains("UK_STATE_MASTER_NAME")) {
				baseDto.setResponse(ErrorCode.STATE_NAME_ALREADY_EXIST);
			}
		}

		return baseDto;
	}
	

	public BaseDto delete(Long id) {

		log.debug("Delete method is Invoked...[" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			
			stateMasterRepository.deleteById(id);
			log.info("State Master Deleted Successfully....[" + id + "]");

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

			List<StateMaster> stateMasterList = stateMasterRepository.findAll();

	
			
			log.info("Successfully getting list of institute...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(stateMasterList);
			
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
