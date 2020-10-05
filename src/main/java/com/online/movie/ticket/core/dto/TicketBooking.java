package com.online.movie.ticket.core.dto;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.online.movie.ticket.enumeration.MovieType;
import com.online.movie.ticket.util.JsonDateDeserializer;
import com.online.movie.ticket.util.JsonDateSerializer;

import lombok.Data;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Data
public class TicketBooking implements java.io.Serializable {
	
	private  String theaterName;
	private String screenName;
	private String movieName;
	private MovieType movieType;
	private String movieDirector;
	private String movieDuration;
	private String movieLanguage;
	private byte[] image;
	private 	String showName;
	
	private String showStartTime;
	private 	String showEndTime;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)		
	private 	Date screenDate;
	
	private Long scheduleScreenShowID;
	

	public byte[] getImage()
	{
		return decompressBytes(this.image);
	}
	private static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}
