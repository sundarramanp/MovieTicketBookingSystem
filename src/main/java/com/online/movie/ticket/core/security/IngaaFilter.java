package com.online.movie.ticket.core.security;

import java.io.IOException;
import java.util.Random;
import org.apache.log4j.MDC;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


import com.online.movie.ticket.util.TrackIdGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * Servlet Filter implementation class IngaaFilter
 */
@WebFilter("/IngaaFilter")
@Log4j2
public class IngaaFilter implements Filter {

	@Getter
	@Setter
	TrackIdGenerator trackIdGenerator;
	
	private String ipAddress = null;

	String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Random rnd = new Random();
	
    /**
     * Default constructor. 
     */
    public IngaaFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.debug("Ingaa Filter destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		log.debug("Ingaa Filter called");

		if (trackIdGenerator != null) {
			String trackId = "Sundar";//trackIdGenerator.getTrackId((HttpServletRequest)request);

			MDC.put("TrackId", trackId);

			log.info("Track Id: " + MDC.get("TrackId"));
		}

		chain.doFilter(request, response);

		MDC.clear();
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
