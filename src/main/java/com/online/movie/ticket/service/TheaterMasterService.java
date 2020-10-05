package com.online.movie.ticket.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import com.online.movie.ticket.repository.TheaterMasterRepository;



import org.springframework.dao.DataIntegrityViolationException;
import com.online.movie.ticket.exception.ExceptionHelper;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.validation.TheaterMasterValidator;
import com.online.movie.ticket.util.TimeUtil;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.TheaterMaster;
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
public class TheaterMasterService {

	@Autowired
	TheaterMasterRepository TheaterMasterRepository;
	
	@Autowired
	TheaterMasterValidator theaterMasterValidator;
	@Transactional(readOnly=true)
	public BaseDto get(Long id) {

		log.debug("get method is called [" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			ValidateUtil.notNull(id, ErrorCode.THEATER_ID_NOT_NULL);
			List<Long> results = new ArrayList<Long>();
			results.add(id);
			List <TheaterMaster> TheaterMaster = TheaterMasterRepository.findAllById(results);
		
			ValidateUtil.notNull(TheaterMaster, ErrorCode.THEATER_ID_NOT_FOUND);

			log.debug("Successfully getting Theater by id.....[" +results+ "]");

			baseDto.setResponse(ErrorCode.SUCCESS);
			if (TheaterMaster.size() >0)
			baseDto.setResponseObject(TheaterMaster.get(0));
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

	public BaseDto save(TheaterMaster TheaterMaster) {
		log.debug("Create method is called.....");

		BaseDto baseDto = new BaseDto();
		try {
			
			theaterMasterValidator.validate(TheaterMaster);
			
			// Setting track time & user...
			TheaterMaster.setSystemTrack(TimeUtil.getCreateSystemTrack());
			// Saving 
			log.info(TheaterMaster.getSystemTrack().getCreateUser()+TheaterMaster.getSystemTrack().getCreateDate());
			TheaterMaster TheaterMasterCreated = TheaterMasterRepository.save(TheaterMaster);
		
			log.debug("Theater Master Saved successfully.....[" + TheaterMasterCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(TheaterMasterCreated);

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());

			baseDto.setResponse(exception.getError());

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

	
			
			if (exceptionCause1.contains("UK_THEATER_NAME")) {
				baseDto.setResponse(ErrorCode.THEATER_NAME_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	public BaseDto update(TheaterMaster existingTheaterMaster) {

		log.debug("update method is called....ID [ " + existingTheaterMaster.getId() + "]");

		BaseDto baseDto = new BaseDto();

		try {

			theaterMasterValidator.validate(existingTheaterMaster);

			
		
			List<Long> id = new ArrayList<Long>();
			id.add(existingTheaterMaster.getId());
			List <TheaterMaster> TheaterMaster = TheaterMasterRepository.findAllById(id);
			
			ValidateUtil.notNull(TheaterMaster, ErrorCode.THEATER_ID_NOT_FOUND);

		

			baseDto.setResponse(ErrorCode.SUCCESS);
			log.info("Message"+TheaterMaster.size());
			if (TheaterMaster.size() >0)
			{
				existingTheaterMaster.setSystemTrack(TheaterMaster.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existingTheaterMaster.getSystemTrack());
			}
			
			
			
			
			

			existingTheaterMaster = TheaterMasterRepository.save(existingTheaterMaster);
			
			log.debug("Theater Master Updated successfully.....[" + existingTheaterMaster.getId() + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

			baseDto.setResponseObject(existingTheaterMaster);

		} catch (RestException exception) {

			log.error("Exception occured in update method ", exception.getMessage());
			
			baseDto.setResponse(exception.getError());
		} catch (ObjectOptimisticLockingFailureException e) {

			log.error("Error in editing TOS", e);

			baseDto.setResponse(ErrorCode.CANNOT_UPDATE_LOCKED_RECORD);
			
		} catch (JpaObjectRetrievalFailureException e) {
			
			log.error("Error in editing TOS", e);
			
			baseDto.setResponse(ErrorCode.CANNOT_UPDATE_DELETED_RECORD);
		
		}
		catch (Exception exception) {
			
			log.error("Exception occurred " + exception);

			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponse(ErrorCode.ERROR_GENERIC);

			
			
			if (exceptionCause1.contains("UK_THEATER_NAME")) {
				baseDto.setResponse(ErrorCode.THEATER_NAME_ALREADY_EXIST);
			}
		}

		return baseDto;
	}
	

	public BaseDto delete(Long id) {

		log.debug("Delete method is Invoked...[" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			
			TheaterMasterRepository.deleteById(id);
			log.info("Theater Master Deleted Successfully....[" + id + "]");

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

			List<TheaterMaster> TheaterMasterList = TheaterMasterRepository.findAll();

	
			
			log.info("Successfully getting list of Theater...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(TheaterMasterList);
			
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