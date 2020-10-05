package com.online.movie.ticket.util;

import java.util.HashMap;
import java.util.Map;

import com.online.movie.ticket.exception.ErrorCode;

/**
 * The Class ParameterFormat.
 */
public class ParameterFormat {

	/** The map. */
	private ParameterFormat() {
	}

	private static Map<String, Object[]> mapExpression;
	static {

		mapExpression = new HashMap<>();



		
	
		mapExpression.put("theaterName",new Object[] { "[A-Z0-9 ]{1,100}", ErrorCode.THEATER_NAME_INVALID  });

		
	}
	/**
	 * Gets the pattern and error message.
	 *
	 * @param key
	 *            the key
	 * @return the pattern and error message
	 */
	public static Object[] getPatternAndErrorMessage(String key) {
		return mapExpression.get(key);
	}
}
