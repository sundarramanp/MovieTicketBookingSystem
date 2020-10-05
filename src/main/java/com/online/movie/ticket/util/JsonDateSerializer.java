package com.online.movie.ticket.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JsonDateSerializer extends JsonSerializer<Date> {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String formattedDate = dateFormat.format(date);
		
		gen.writeString(formattedDate);
	}
}
