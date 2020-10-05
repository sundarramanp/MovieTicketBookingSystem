package com.online.movie.ticket.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import com.online.movie.ticket.repository.MovieMasterRepository;
import com.online.movie.ticket.validation.MovieMasterValidator;

import org.springframework.dao.DataIntegrityViolationException;
import com.online.movie.ticket.exception.ExceptionHelper;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.util.TimeUtil;
import com.online.movie.ticket.exception.ErrorCode;

import com.online.movie.ticket.model.MovieMaster;
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
public class MovieMasterService {

	@Autowired 
	MovieMasterRepository movieMasterRepository;
	
	@Autowired
	MovieMasterValidator movieMasterValidator;
	
	@Transactional(readOnly=true)
	public BaseDto get(Long id) {

		log.debug("get method is called [" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			ValidateUtil.notNull(id, ErrorCode.MOVIE_ID_NOT_NULL);
			List<Long> results = new ArrayList<Long>();
			results.add(id);
			List <MovieMaster> movieMaster = movieMasterRepository.findAllById(results);
		
			ValidateUtil.notNull(movieMaster, ErrorCode.MOVIE_ID_NOT_NULL);

			log.info("Successfully getting State by id.....[" +results+ "]");

			baseDto.setResponse(ErrorCode.SUCCESS);
			if (movieMaster.size() >0)
			baseDto.setResponseObject(movieMaster.get(0));
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
	

	public BaseDto create(MovieMaster movieMaster) {
		log.debug("Create method is called.....");

		BaseDto baseDto = new BaseDto();
		try {
			
			movieMasterValidator.validate(movieMaster);
			
			// Setting track time & user...
			movieMaster.setSystemTrack(TimeUtil.getCreateSystemTrack());
			// Saving 
			MovieMaster movieMasterCreated = movieMasterRepository.save(movieMaster);
		
			log.debug("movie Master Saved successfully.....[" + movieMasterCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(movieMasterCreated);

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
	

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

		
			
			if (exceptionCause1.contains("UK_MOVIE_MASTER")) {
				baseDto.setResponse(ErrorCode.MOVIE_NAME_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	public BaseDto update(MovieMaster existingmovieMaster) {

		log.debug("update method is called....ID [ " + existingmovieMaster.getId() + "]");

		BaseDto baseDto = new BaseDto();

		try {

			movieMasterValidator.validate(existingmovieMaster);

			
		
			List<Long> id = new ArrayList<Long>();
			id.add(existingmovieMaster.getId());
			List <MovieMaster> movieMaster = movieMasterRepository.findAllById(id);
			
			ValidateUtil.notNull(movieMaster, ErrorCode.MOVIE_NOT_FOUND);

		

			baseDto.setResponse(ErrorCode.SUCCESS);
			
			if (movieMaster.size() >0)
			{
				existingmovieMaster.setSystemTrack(movieMaster.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existingmovieMaster.getSystemTrack());
			}
			
			
			
			
			

			existingmovieMaster = movieMasterRepository.save(existingmovieMaster);
			
			log.debug("movie Master Updated successfully.....[" + existingmovieMaster.getId() + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

			baseDto.setResponseObject(existingmovieMaster);

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

		
			if (exceptionCause1.contains("UK_MOVIE_MASTER")) {
				baseDto.setResponse(ErrorCode.MOVIE_NAME_ALREADY_EXIST);
			}
		}

		return baseDto;
	}
	

	public BaseDto delete(Long id) {

		log.debug("Delete method is Invoked...[" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			
			movieMasterRepository.deleteById(id);
			log.info("movie Master Deleted Successfully....[" + id + "]");

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

			List<MovieMaster> movieMasterList = movieMasterRepository.findAll();

	
			
			log.info("Successfully getting list of institute...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(movieMasterList);
			
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
