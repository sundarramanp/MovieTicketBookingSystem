package com.online.movie.ticket.util;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.*;


import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AppUtil {
	
	public List<Class> getAllClasses(String pckgname) {
		try {
			List<Class> classes = new ArrayList();
			classes.add(MovieShowStatus.class);
			classes.add(MovieType.class);
			classes.add(ScheduleStatus.class);
			classes.add(VisibilityStatus.class);
			
			
			
			return classes;

		} catch (Exception e) {
			log.error("Exception getting all enum class:", e);
			return null;
		}
	}
    
}
