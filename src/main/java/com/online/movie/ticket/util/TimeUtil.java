package com.online.movie.ticket.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.model.SystemTrack;
import com.online.movie.ticket.service.AuthUserDetailService;

public class TimeUtil {

	private TimeUtil() {

	}
	
	public static Date getCurrentLocationTime() {
		String timeZone = "Asia/Kolkata";
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of(timeZone));
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);

	}

	public static SystemTrack getCreateSystemTrack() {

		SystemTrack systemTrack = new SystemTrack();

		systemTrack.setCreateDate(getCurrentLocationTime());
		systemTrack.setLastUpdatedDate(getCurrentLocationTime());
		
		try {
			systemTrack.setCreateUser(AuthUserDetailService.getCurrentUser().getLoginID());
			systemTrack.setLastUpdatedUser(AuthUserDetailService.getCurrentUser().getLoginID());
		}
		 catch (Exception exception) 
		{
			 systemTrack.setCreateUser("ADMIN");
				systemTrack.setLastUpdatedUser("ADMIN");
		}
		

		return systemTrack;
	}

	public static void setUpdateSystemTrack(SystemTrack systemTrack) {

		if (systemTrack == null) {
			systemTrack = new SystemTrack();
			systemTrack.setCreateDate(getCurrentLocationTime());
			systemTrack.setCreateUser(AuthUserDetailService.getCurrentUser().getLoginID());
		}
		systemTrack.setLastUpdatedDate(getCurrentLocationTime());
		systemTrack.setLastUpdatedUser(AuthUserDetailService.getCurrentUser().getLoginID());

	}
	
	

	public static Date getServerDateTime() {
		return new Date();
	}

	public static String getServerDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

	public static Date addMinutesToDate(int minutes, Date beforeTime){
		final long ONE_MINUTE_IN_MILLIS = 60000;
		long curTimeInMs = beforeTime.getTime();
		Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
		return afterAddingMins;
	}
}
