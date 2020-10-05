package com.online.movie.ticket.service;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.util.AppUtil;

import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.exception.ErrorCode;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AppService {

	@Autowired
	AppUtil appUtil;
	
	public BaseDto getAllEnums() {
		log.info("getAllEnums");
	
		Map<String, Object[]> map = new HashMap<>();
		
		for (Class className : appUtil.getAllClasses(VisibilityStatus.class.getName().replaceAll("." + VisibilityStatus.class.getSimpleName(), ""))) {

			List<String> values = new ArrayList<>();

			for (Field field : className.getDeclaredFields()) {
				if (!field.getName().contains("$")) {
					values.add(field.getName());
				}

			}

			map.put(className.getSimpleName(), values.toArray());
		}

		BaseDto baseDto = new BaseDto();
		baseDto.setResponse(ErrorCode.SUCCESS);
		baseDto.setResponseObject(map);

		return baseDto;
	}
}
