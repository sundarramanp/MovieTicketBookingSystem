package com.online.movie.ticket.core.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.online.movie.ticket.util.JsonDateDeserializer;
import com.online.movie.ticket.util.JsonDateSerializer;

import lombok.Data;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Data
public class ScreenShowDetails implements java.io.Serializable {
	
	private  String theaterName;
	private String screenName;
	private 	String showName;
	private String movieName;
	private String showStartTime;
	private 	String showEndTime;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)		
	private 	Date screenDate;
	
	private Long scheduleScreenShowID;
	private Long bookedTicket;
	private Long openTicket;
	
}
