package com.online.movie.ticket.service;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import com.online.movie.ticket.repository.ScheduleScreenShowRepository;
import com.online.movie.ticket.repository.impl.ScheduleScreenShowImpl;
import com.online.movie.ticket.validation.ScheduleScreenShowValidator;

import org.springframework.dao.DataIntegrityViolationException;
import com.online.movie.ticket.exception.ExceptionHelper;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.util.TimeUtil;
import com.online.movie.ticket.exception.ErrorCode;

import com.online.movie.ticket.model.ScheduleScreenShow;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.core.dto.ScreenShowDetails;
import com.online.movie.ticket.core.dto.TicketBooking;

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
public class ScheduleScreenShowService {

	@Autowired 
	ScheduleScreenShowRepository scheduleScreenShowRepository;
	
	@Autowired
	ScheduleScreenShowValidator scheduleScreenShowValidator;


	@Autowired
	ScheduleScreenShowImpl scheduleScreenShowImpl;
	
	@Transactional(readOnly=true)
	public BaseDto get(Long id) {

		log.debug("get method is called [" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			ValidateUtil.notNull(id, ErrorCode.SCREEN_SHOW_NOT_NULL);
			List<Long> results = new ArrayList<Long>();
			results.add(id);
			List <ScheduleScreenShow> scheduleScreenShow = scheduleScreenShowRepository.findAllById(results);
		
			ValidateUtil.notNull(scheduleScreenShow, ErrorCode.SCREEN_SHOW_NOT_NULL);

			log.info("Successfully getting State by id.....[" +results+ "]");

			baseDto.setResponse(ErrorCode.SUCCESS);
			if (scheduleScreenShow.size() >0)
			baseDto.setResponseObject(scheduleScreenShow.get(0));
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
	

	public BaseDto create(ScheduleScreenShow scheduleScreenShow) {
		log.debug("Create method is called.....");

		BaseDto baseDto = new BaseDto();
		try {
			
			scheduleScreenShowValidator.validate(scheduleScreenShow);
			
			// Setting track time & user...
			scheduleScreenShow.setSystemTrack(TimeUtil.getCreateSystemTrack());
			// Saving 
			ScheduleScreenShow scheduleScreenShowCreated = scheduleScreenShowRepository.save(scheduleScreenShow);
		
			log.debug("scheduleScreenShow Master Saved successfully.....[" + scheduleScreenShowCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(scheduleScreenShowCreated);

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
	

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

		
			
			if (exceptionCause1.contains("UK_SCHEDULE_SCREEN_SHOW")) {
				baseDto.setResponse(ErrorCode.SCHEDULE_SCREEN_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	public BaseDto update(ScheduleScreenShow existingscheduleScreenShow) {

		log.debug("update method is called....ID [ " + existingscheduleScreenShow.getId() + "]");

		BaseDto baseDto = new BaseDto();

		try {

			scheduleScreenShowValidator.validate(existingscheduleScreenShow);

			
		
			List<Long> id = new ArrayList<Long>();
			id.add(existingscheduleScreenShow.getId());
			List <ScheduleScreenShow> scheduleScreenShow = scheduleScreenShowRepository.findAllById(id);
			
			ValidateUtil.notNull(scheduleScreenShow, ErrorCode.SCREEN_SHOW_NOT_FOUND);

		

			baseDto.setResponse(ErrorCode.SUCCESS);
			
			if (scheduleScreenShow.size() >0)
			{
				existingscheduleScreenShow.setSystemTrack(scheduleScreenShow.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existingscheduleScreenShow.getSystemTrack());
			}
			
			
			
			
			

			existingscheduleScreenShow = scheduleScreenShowRepository.save(existingscheduleScreenShow);
			
			log.debug("scheduleScreenShow Master Updated successfully.....[" + existingscheduleScreenShow.getId() + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

			baseDto.setResponseObject(existingscheduleScreenShow);

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

			
			if (exceptionCause1.contains("UK_SCHEDULE_SCREEN_SHOW")) {
				baseDto.setResponse(ErrorCode.SCHEDULE_SCREEN_ALREADY_EXIST);
			}
		}

		return baseDto;
	}
	

	public BaseDto delete(Long id) {

		log.debug("Delete method is Invoked...[" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			
			scheduleScreenShowRepository.deleteById(id);
			log.info("scheduleScreenShow Master Deleted Successfully....[" + id + "]");

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

			List<ScheduleScreenShow> scheduleScreenShowList = scheduleScreenShowRepository.findAll();

	
			
			log.info("Successfully getting list of institute...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(scheduleScreenShowList);
			
		} catch (RestException re) {
			log.error("Exception in getAll method ", re.getMessage());
			baseDto.setResponse(re.getError());
			
		} catch (Exception e) {
			log.error("Exception in getAll method ", e);
			baseDto.setResponse(ErrorCode.FAILED);
			
		}
		return baseDto;
	}
	
	public BaseDto getAllShow() {

		log.info("getAllShow method is called....");

		BaseDto baseDto = new BaseDto();

		try {

			List<ScreenShowDetails> scheduleScreenShowList = scheduleScreenShowImpl.getScreenShow();

	
			
			log.info("Successfully getting list of institute...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(scheduleScreenShowList);
			
		} catch (RestException re) {
			log.error("Exception in getAll method ", re.getMessage());
			baseDto.setResponse(re.getError());
			
		} catch (Exception e) {
			log.error("Exception in getAll method ", e);
			baseDto.setResponse(ErrorCode.FAILED);
			
		}
		return baseDto;
	}
	

	public BaseDto getBookingList() {

		log.info("Get BookingList method is called....");

		BaseDto baseDto = new BaseDto();

		try {

			List<TicketBooking> scheduleScreenShowList = scheduleScreenShowImpl.getBookingList();

	
			
			log.info("Successfully getting BookingList...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(scheduleScreenShowList);
			
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
