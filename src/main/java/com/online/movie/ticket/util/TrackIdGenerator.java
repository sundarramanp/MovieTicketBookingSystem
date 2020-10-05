package com.online.movie.ticket.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TrackIdGenerator {

	private String ipAddress = null;

	String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Random rnd = new Random();

	public synchronized String getTrackId(HttpServletRequest httpRequest) {
		StringBuilder sb = new StringBuilder(8);

		String saas = httpRequest.getHeader("SAAS_ID");

		if (saas != null && saas.trim().length() != 0) {
			sb.append("SAAS#" + saas + "#-");
		}

		if (ipAddress == null) {
		    getIP();
		}

		sb.append(ipAddress);

		sb.append("-");

		for (int i = 0; i < 8; i++) {
		    sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}

		sb.append("-");

		sb.append(getServerDateTime());

		
		
		
		return sb.toString();
	}

	private String getIP() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			ipAddress = ip.getHostAddress();
		} catch (UnknownHostException e) {
			//log.warn("UnknownHostException : ", e);
			ipAddress = "";
		}
		return ipAddress;
	}

	private String getServerDateTime() {
 	    
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
