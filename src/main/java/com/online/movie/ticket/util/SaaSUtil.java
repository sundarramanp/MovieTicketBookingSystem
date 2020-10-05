package com.online.movie.ticket.util;

import org.apache.log4j.MDC;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SaaSUtil {

	private SaaSUtil() {

	}

	public static String getSaaSId() {

		String trackId = (String) MDC.get("TrackId");

		if (trackId == null) {
			log.debug("TrackId is null or Empty");
			return "";
		}

		String[] trackIds = trackId.split("#");

		if ("SAAS".equals(trackIds[0])) {
			return trackIds[1];
		}

		return "";

	}

}