package com.online.movie.ticket.exception;



import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Sundar
 *
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
    	
        // Let Spring handle the error first, we will modify later :)
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> userDefinedErrorAttributes = new HashMap<>();
        userDefinedErrorAttributes.put("trackId", "");	
        Object responseCode = errorAttributes.get("status");
        if (responseCode == null) 
        {
        	userDefinedErrorAttributes.put("responseCode", ErrorCode.ERROR_GENERIC);
        }
        else
        {
        	userDefinedErrorAttributes.put("responseCode", errorAttributes.get("status"));
        }
        userDefinedErrorAttributes.put("responseDescription", errorAttributes.get("error")+" / "+errorAttributes.get("message"));
        userDefinedErrorAttributes.put("responseObject",errorAttributes);	
        userDefinedErrorAttributes.put("requestURL",errorAttributes.get("path"));	
        
        	    
        
        Object timestamp = errorAttributes.get("timestamp");
        if (timestamp == null) {
        	userDefinedErrorAttributes.put("timestamp", dateFormat.format(new Date()));
        } else {
        	userDefinedErrorAttributes.put("timestamp", dateFormat.format((Date) timestamp));
        }


        return userDefinedErrorAttributes;

    }

}