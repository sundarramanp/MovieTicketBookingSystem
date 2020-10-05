package com.online.movie.ticket.core.dto;

import com.online.movie.ticket.exception.ErrorCode;

//import org.apache.log4j.MDC;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Sundar
 *
 */

public class BaseDto{

	@Getter @Setter String trackId;
	
	@Getter @Setter String responseCode;
	
	@Getter @Setter String responseDescription;

	@Getter @Setter Object responseObject;
	
	@Getter @Setter Object requestURL;
	
	public BaseDto(){
	//	trackId = (String) MDC.get("TrackId");
	//	log.info("BaseDto trackId:"+trackId);
	}
	public void setResponse(ErrorCode msg){
		this.responseCode = msg.getCode();
		this.responseDescription = msg.getDescription();
	//	trackId = (String) MDC.get("TrackId");
	//	log.info("BaseDto trackId:"+trackId);
	}
}
