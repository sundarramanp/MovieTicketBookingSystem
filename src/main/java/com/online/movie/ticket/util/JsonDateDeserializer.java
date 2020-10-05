package com.online.movie.ticket.util;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JsonDateDeserializer extends JsonDeserializer<Date> {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException {

		String string = jsonparser.getText();
		Date date = null;
		try {
			date = dateFormat.parse(string);
		} catch (ParseException e) {
			log.error("Date paraser invaild 1 ->[" + string + "], " + e.getMessage());
		}
		
		if (date == null)
		{
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = dateFormat.parse(string);
			} catch (ParseException e) {
				log.error("Date paraser invaild [" + string + "], " +date);
			}
		}

		return date;

	}

}
