package com.online.movie.ticket.repository.impl;

import javax.persistence.Query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.movie.ticket.core.dto.ScreenShowDetails;
import com.online.movie.ticket.core.dto.TicketBooking;
import com.online.movie.ticket.model.ScreenShow;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class ScheduleScreenShowImpl {


	@Autowired
	EntityManager em;
		
	public List<ScreenShowDetails> getScreenShow() {
		
				String hql;
	
				
				hql = "SELECT NEW  com.online.movie.ticket.core.dto.ScreenShowDetails(theaterMaster.theaterName , "
						+ "screenMaster.screenName ,"
						+ "showMaster.showName ,"
						+ "movieMaster.movieName ,"
						+ "showMaster.showStartTime ,"
						+ "showMaster.showEndTime ,  " 
						+ "scheduleScreenShow.screenDate ,"
						+ "scheduleScreenShow.id , "
						+ "SUM(case when screenSeatBooking.status ='BOOKED' then 1 end) ,"
						+ "SUM(case when screenSeatBooking.status ='OPEN' then 1 end) ) " + 
						" FROM TheaterMaster as theaterMaster   " + 
						" inner join ScreenMaster as screenMaster  on theaterMaster.id = screenMaster.theaterMaster.id " + 
						" inner join ShowMaster as showMaster on showMaster.screenMaster.id = screenMaster.id  " + 
						" inner join ScreenShow as screenShow  on screenShow.screenMaster.id = screenMaster.id and screenShow.showMaster.id = showMaster.id  " + 
						" inner join MovieMaster as movieMaster on movieMaster.id = screenShow.movieMaster.id " + 
						" inner join ScheduleScreenShow as scheduleScreenShow on scheduleScreenShow.screenShow.id = screenShow.id \n" + 
						" inner join ScreenSeatBooking as screenSeatBooking on screenSeatBooking.scheduleScreenShow.id = scheduleScreenShow.id" +
						" where screenMaster.status ='ACTIVE' " + 
						" and theaterMaster.status ='ACTIVE'  " + 
						" and scheduleScreenShow.scheduleStatus  ='ACTIVE'  " + 
						" group by screenMaster.screenName, showMaster.showName,movieMaster.movieName,showMaster.showStartTime,"
						+ "showMaster.showEndTime,scheduleScreenShow.screenDate,scheduleScreenShow.id "
						+ " order by scheduleScreenShow.screenDate ";
				
				log.info(hql);
				TypedQuery<ScreenShowDetails> query =  em.createQuery(hql, ScreenShowDetails.class);
				
				log.info("First"); 
				long countTotal = query.getResultList().size();
				log.info("countTotal :"+countTotal); 
				List<ScreenShowDetails> resultList = (List<ScreenShowDetails>) query.getResultList();
				
				
				return resultList;
	
		
	}

	public List<TicketBooking> getBookingList() {
		
				String hql;
	
				
				hql = "SELECT NEW  com.online.movie.ticket.core.dto.TicketBooking(theaterMaster.theaterName , "
						+ "screenMaster.screenName ,"						
						+ "movieMaster.movieName ,"
						+ "movieMaster.movieType ,"
						+ "movieMaster.movieDirector ,"
						+ "movieMaster.movieDuration ,"
						+ "movieMaster.movieLanguage ,"
						+ "movieSnaps.image ,"
						+ "showMaster.showName ,"
						+ "showMaster.showStartTime ,"
						+ "showMaster.showEndTime ,  " 
						+ "scheduleScreenShow.screenDate ,"
						+ "scheduleScreenShow.id)"+
						" FROM TheaterMaster as theaterMaster   " + 
						" inner join ScreenMaster as screenMaster  on theaterMaster.id = screenMaster.theaterMaster.id " + 
						" inner join ShowMaster as showMaster on showMaster.screenMaster.id = screenMaster.id  " + 
						" inner join ScreenShow as screenShow  on screenShow.screenMaster.id = screenMaster.id and screenShow.showMaster.id = showMaster.id  " + 
						" inner join MovieMaster as movieMaster on movieMaster.id = screenShow.movieMaster.id " + 
						" inner join MovieSnaps as movieSnaps on movieMaster.id = movieSnaps.movieMaster.id " + 
						" inner join ScheduleScreenShow as scheduleScreenShow on scheduleScreenShow.screenShow.id = screenShow.id \n" + 
						" where screenMaster.status ='ACTIVE' " + 
						" and theaterMaster.status ='ACTIVE'  " + 
						" and scheduleScreenShow.scheduleStatus  ='ACTIVE'  " + 
						" ORDER BY scheduleScreenShow.screenDate,theaterMaster.theaterName,movieMaster.movieName,showMaster.showStartTime";
				
				log.info(hql);
				TypedQuery<TicketBooking> query =  em.createQuery(hql, TicketBooking.class);
				
				log.info("First"); 
				long countTotal = query.getResultList().size();
				log.info("countTotal :"+countTotal); 
				List<TicketBooking> resultList = (List<TicketBooking>) query.getResultList();
				
				
				return resultList;
	
		
	}
	
	
}
