package com.online.movie.ticket.util;

import org.apache.commons.lang.RandomStringUtils;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class AuthUtil {

	public static String genarateResetTokenKey() {
		log.info("AuthUtil.genarateResetTokenKey called.");
		String RandomStringUtilsInput = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String resetTokenKey =  RandomStringUtils.random(13, RandomStringUtilsInput);
		return resetTokenKey;
	}

}
