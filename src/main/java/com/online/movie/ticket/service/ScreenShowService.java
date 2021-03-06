package com.online.movie.ticket.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import com.online.movie.ticket.repository.ScreenShowRepository;
import com.online.movie.ticket.validation.ScreenShowValidator;

import org.springframework.dao.DataIntegrityViolationException;
import com.online.movie.ticket.exception.ExceptionHelper;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.util.ValidateUtil;
import com.online.movie.ticket.util.TimeUtil;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.ScheduleScreenShow;
import com.online.movie.ticket.model.ScreenSeatBooking;
import com.online.movie.ticket.model.ScreenShow;
import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.enumeration.MovieShowStatus;
import com.online.movie.ticket.enumeration.ScheduleStatus;
import com.online.movie.ticket.enumeration.TicketStatus;

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
public class ScreenShowService {

	@Autowired 
	ScreenShowRepository screenShowRepository;
	
	@Autowired
	ScreenShowValidator screenShowValidator;
	
	@Autowired
	ScheduleScreenShowService scheduleScreenShowService;
	
	@Autowired
	ScreenSeatBookingService screenSeatBookingService;
	
	
	@Transactional(readOnly=true)
	public BaseDto get(Long id) {

		log.debug("get method is called [" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			ValidateUtil.notNull(id, ErrorCode.SCREEN_SHOW_NOT_NULL);
			List<Long> results = new ArrayList<Long>();
			results.add(id);
			List <ScreenShow> ScreenShow = screenShowRepository.findAllById(results);
		
			ValidateUtil.notNull(ScreenShow, ErrorCode.SCREEN_SHOW_NOT_NULL);

			log.info("Successfully getting State by id.....[" +results+ "]");

			baseDto.setResponse(ErrorCode.SUCCESS);
			if (ScreenShow.size() >0)
			baseDto.setResponseObject(ScreenShow.get(0));
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
	

	public BaseDto create(ScreenShow screenShow) {
		log.debug("Create method is called.....");

		BaseDto baseDto = new BaseDto();
		try {
			
			screenShowValidator.validate(screenShow);
			
			// Setting track time & user...
			screenShow.setSystemTrack(TimeUtil.getCreateSystemTrack());
			// Saving 
			screenShow.setScreenStatus(MovieShowStatus.OPEN);
			ScreenShow screenShowCreated = screenShowRepository.save(screenShow);
		
			log.debug("screenShow Master Saved successfully.....[" + screenShowCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(screenShowCreated);

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
	

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

		
			
			if (exceptionCause1.contains("UK_SCREEN_SHOW")) {
				baseDto.setResponse(ErrorCode.SCREEN_SHOW_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	public BaseDto update(ScreenShow existingScreenShow) {

		log.debug("update method is called....ID [ " + existingScreenShow.getId() + "]");

		BaseDto baseDto = new BaseDto();

		try {

			screenShowValidator.validate(existingScreenShow);

			
		
			List<Long> id = new ArrayList<Long>();
			id.add(existingScreenShow.getId());
			List <ScreenShow> screenShow = screenShowRepository.findAllById(id);
			
			ValidateUtil.notNull(screenShow, ErrorCode.SCREEN_SHOW_NOT_FOUND);

		

			baseDto.setResponse(ErrorCode.SUCCESS);
			
			if (screenShow.size() >0)
			{
				existingScreenShow.setSystemTrack(screenShow.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existingScreenShow.getSystemTrack());
			}
			
			
			
			
			

			existingScreenShow = screenShowRepository.save(existingScreenShow);
			
			log.debug("screenShow Master Updated successfully.....[" + existingScreenShow.getId() + "]");

			baseDto.setResponse(ErrorCode.SUCCESS);

			baseDto.setResponseObject(existingScreenShow);

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

			
			if (exceptionCause1.contains("UK_SCREEN_SHOW")) {
				baseDto.setResponse(ErrorCode.SCREEN_SHOW_ALREADY_EXIST);
			}
		}

		return baseDto;
	}
	

	public BaseDto delete(Long id) {

		log.debug("Delete method is Invoked...[" + id + "]");

		BaseDto baseDto = new BaseDto();

		try {

			
			screenShowRepository.deleteById(id);
			log.info("ScreenShow Master Deleted Successfully....[" + id + "]");

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

			List<ScreenShow> screenShowList = screenShowRepository.findAll();

	
			
			log.info("Successfully getting list of institute...");
		
			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(screenShowList);
			
		} catch (RestException re) {
			log.error("Exception in getAll method ", re.getMessage());
			baseDto.setResponse(re.getError());
			
		} catch (Exception e) {
			log.error("Exception in getAll method ", e);
			baseDto.setResponse(ErrorCode.FAILED);
			
		}
		return baseDto;
	}
	
	@Transactional(readOnly=false)
	public BaseDto schedule(ScreenShow existingScreenShow) {
		log.debug("schedule method is called.....");

		BaseDto baseDto = new BaseDto();
		try {
			
			screenShowValidator.validate(existingScreenShow);
			List<Long> id = new ArrayList<Long>();
			id.add(existingScreenShow.getId());
			List <ScreenShow> screenShow = screenShowRepository.findAllById(id);
			
			ValidateUtil.notNull(screenShow, ErrorCode.SCREEN_SHOW_NOT_FOUND);

			baseDto.setResponse(ErrorCode.SUCCESS);
			
			if (screenShow.size() >0)
			{
				existingScreenShow.setSystemTrack(screenShow.get(0).getSystemTrack());
				TimeUtil.setUpdateSystemTrack(existingScreenShow.getSystemTrack());
			}
			
			
			Calendar firstDate = Calendar.getInstance(); 	
			firstDate.setTime(existingScreenShow.getStartDate()); 

			while(firstDate.getTime().before(existingScreenShow.getEndDate()))
			{
				firstDate.add(Calendar.DATE, 1); 
				ScheduleScreenShow scheduleScreenShow = new ScheduleScreenShow();
				scheduleScreenShow.setScreenShow(existingScreenShow);
				scheduleScreenShow.setScreenDate(firstDate.getTime());
				scheduleScreenShow.setScheduleStatus(ScheduleStatus.ACTIVE);
				scheduleScreenShowService.create(scheduleScreenShow);
				insertSeat(scheduleScreenShow);
			}
			
			existingScreenShow.setScreenStatus(MovieShowStatus.ACTIVE);
			ScreenShow screenShowCreated = screenShowRepository.save(existingScreenShow);
		
			log.debug("screenShow Master Saved successfully.....[" + existingScreenShow.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject(screenShow);

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
	

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());

		
			
			if (exceptionCause1.contains("UK_SCREEN_SHOW")) {
				baseDto.setResponse(ErrorCode.SCREEN_SHOW_ALREADY_EXIST);
			}
		}
		return baseDto;
	}
	
	void insertSeat(ScheduleScreenShow scheduleScreenShow)
	{
		int tikcet =0;
		for(int i=1;i<=scheduleScreenShow.getScreenShow().getScreenMaster().getNumberOfRow();i++)
		{
			for (int j=1;j<=scheduleScreenShow.getScreenShow().getScreenMaster().getNumberOfColumn();j++)
			{
				tikcet++;
				ScreenSeatBooking screenSeatBooking = new ScreenSeatBooking();
				screenSeatBooking.setScheduleScreenShow(scheduleScreenShow);
				screenSeatBooking.setSeatNo(tikcet);
				screenSeatBooking.setStatus(TicketStatus.OPEN);
				screenSeatBookingService.create(screenSeatBooking);
			}
		}
	}	
	
}
