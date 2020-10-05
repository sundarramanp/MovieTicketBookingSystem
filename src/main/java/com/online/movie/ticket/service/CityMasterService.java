package com.online.movie.ticket.service;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import com.online.movie.ticket.repository.CityMasterRepository;
import com.online.movie.ticket.validation.CityMasterValidator;

import org.springframework.dao.DataIntegrityViolationException;
import com.online.movie.ticket.exception.ExceptionHelper;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.util.TimeUtil;
import com.online.movie.ticket.exception.ErrorCode;

import com.online.movie.ticket.model.CityMaster;
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
public class CityMasterService {

	@Autowired 
	CityMasterRepository cityMasterRepository;
	
	@Autowired
	CityMasterValidator cityMasterValidator;
	
	@Transactional(readOnly=true)
	public BaseDto get(Long id) {

		log.debug("get method is called [" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			ValidateUtil.notNull(id, ErrorCode.CITY_ID_NOT_NULL);
			List<Long> results = new ArrayList<Long>();
			results.add(id);
			List <CityMaster> cityMaster = cityMasterRepository.findAllById(results);
		
			ValidateUtil.notNull(cityMaster, ErrorCode.CITY_ID_NOT_FOUND);

			log.info("Successfully getting city by id.....[" +results+ "]");

			baseDto.setResponse(ErrorCode.SUCCESS);
			if (cityMaster.size() >0)
			baseDto.setResponseObject(cityMaster.get(0));
			else
				baseDto.setResponseObject(null);	

		} catch (RestException ex) {

			
			baseDto.setResponse(ex.getError());


		} catch (Exception e) {

			log.error("Exception in get method ", e);

			baseDto.setResponse(ErrorCode.FAILED);
		}

		return baseDto;
	}
	

	public BaseDto create(CityMaster cityMaster) {
		log.debug("Create method is called.....");

		BaseDto baseDto = new BaseDto();
		try {
			
			cityMasterValidator.validate(cityMaster);
			
			// Setting track time & user...
			cityMaster.setSystemTrack(TimeUtil.getCreateSystemTrack());
			// Saving 
			CityMaster cityMasterCreated = cityMasterRepository.save(cityMaster);
		
			log.debug("City Master Saved successfully.....[" + cityMasterCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(cityMasterCreated);

		} catch (RestException exception) {

			log.error("Bad Request Exception occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
			//baseDto.setResponseCode(exception.getMessage());

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

			if (exceptionCause1.contains("UK_CITY_CITYCODE")) {
				baseDto.setResponse(ErrorCode.CITY_CODE_ALREADY_EXIST);
			} 
			
			if (exceptionCause1.contains("UK_CITY_CITYNAME")) {
				baseDto.setResponse(ErrorCode.CITY_NAME_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	public BaseDto update(CityMaster existingcityMaster) {

		log.debug("update method is called....ID [ " + existingcityMaster.getId() + "]");

		BaseDto baseDto = new BaseDto();

		try {

			cityMasterValidator.validate(existingcityMaster);

			
		
			List<Long> id = new ArrayList<Long>();
			id.add(existingcityMaster.getId());
			List <CityMaster> cityMaster = cityMasterRepository.findAllById(id);
			
			ValidateUtil.notNull(cityMaster, ErrorCode.CITY_ID_NOT_FOUND);

		

			baseDto.setResponse(ErrorCode.SUCCESS);
			
			if (cityMaster.size() >0)
			{
				existingcityMaster.setSystemTrack(cityMaster.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existingcityMaster.getSystemTrack());
			}
			
			
			
			
			

			existingcityMaster = cityMasterRepository.save(existingcityMaster);
			
			log.debug("City Master Updated successfully.....[" + existingcityMaster.getId() + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

			baseDto.setResponseObject(existingcityMaster);

		} catch (RestException exception) {

			log.error("Exception occured in update method ", exception.getMessage());

			baseDto.setResponse(exception.getError());

		} catch (ObjectOptimisticLockingFailureException e) {

			log.error("Error in editing cityMaster", e);

			baseDto.setResponse(ErrorCode.CANNOT_UPDATE_LOCKED_RECORD);
			
		} catch (JpaObjectRetrievalFailureException e) {
			
			log.error("Error in editing cityMaster", e);
			
			baseDto.setResponse(ErrorCode.CANNOT_UPDATE_DELETED_RECORD);
		
		}
		catch (Exception exception) {
			
			log.error("Exception occurred " + exception);

			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponse(ErrorCode.ERROR_GENERIC);

			if (exceptionCause1.contains("UK_CITY_CITYCODE")) {
				baseDto.setResponse(ErrorCode.CITY_CODE_ALREADY_EXIST);
			} 
			
			if (exceptionCause1.contains("UK_CITY_CITYNAME")) {
				baseDto.setResponse(ErrorCode.CITY_NAME_ALREADY_EXIST);
			}
		}

		return baseDto;
	}
	

	public BaseDto delete(Long id) {

		log.debug("Delete method is Invoked...[" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			
			cityMasterRepository.deleteById(id);
			log.info("City Master Deleted Successfully....[" + id + "]");

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

			List<CityMaster> cityMasterList = cityMasterRepository.findAll();

	
			
			log.info("Successfully getting list of institute...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(cityMasterList);
			
		} catch (RestException re) {
			log.error("Exception in getAll method ", re);
			baseDto.setResponseCode(re.getMessage());
			
		} catch (Exception e) {
			log.error("Exception in getAll method ", e);
			baseDto.setResponse(ErrorCode.FAILED);
			
		}
		return baseDto;
	}
}