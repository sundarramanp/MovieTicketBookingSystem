package com.online.movie.ticket.util;


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.exception.ErrorCode;

import lombok.extern.log4j.Log4j2;


/**
 * The Class Validate.
 */

@Log4j2
public class ValidateUtil {

	/**
     * Not null.
     *
     * @param <E> the element type
     * @param string the object
     * @param b the parameter name
     * @return the e
     * @throws RestException the rest exception
     */
   
	
	
    /**
     * Assert true.
     *
     * @param flag the flag
     * @param errorCode the error code
     * @throws RestException the rest exception
     */ 
 

    /**
     * Not null.
     *
     * @param <E> the element type
     * @param object the object
     * @param parameterName the parameter name
     * @param checkPattern the check pattern
     * @return the e
     * @throws RestException the rest exception
     */
    public static <E> E checkPattern(E object, String parameterName, boolean checkPattern) throws RestException {
        if (checkPattern) {
            checkPattern(object, parameterName);
        }
        return object;
    }

    /**
     * Not null.
     *
     * @param object the object
     * @param errorCode the error code
     * @throws RestException the rest exception
     */
    public static void notNull(Object object, ErrorCode error) throws RestException {
        if (object == null) {
			throw new RestException(error);
		}
    }

    /**
     * Check pattern.
     *
     * @param <E> the element type
     * @param object the object
     * @param parameterName the parameter name
     * @return the e
     * @throws RestException the rest exception
     */
    public static <E> E checkPattern(E object,String parameterName) throws RestException {
        if (object == null) {
			return null;
		}
        Object[] patternAndErrorMessage = ParameterFormat.getPatternAndErrorMessage(parameterName);
       
        if (patternAndErrorMessage== null) {
			return object;
		}
        String parameterPattern = patternAndErrorMessage[0].toString();
        ErrorCode errorMessage = (ErrorCode)patternAndErrorMessage[1];
        Pattern pattern = Pattern.compile(parameterPattern);
        Matcher matcher = pattern.matcher(object.toString());
        boolean matchFound = matcher.matches();
        if (!matchFound) {
            // String message = "Parameter '" + parameterName + "' is in invalid format";
            RestException ex = new RestException(errorMessage);
            throw ex;
        }
        return object;
    }
    
    /**
     * Not empty - Used to check the collection is empty.
     * @param list the list
     * @param errorCode the error code
     * @throws RestException the rest exception
     */
    public static void notEmpty(@SuppressWarnings("rawtypes") Collection list,
	    ErrorCode error) throws RestException {
		if (list == null || list.size() == 0) {
			throw new RestException(error);
		}
	}
    
    /**
     * Not empty - Used to check the String is empty.
     *
     * @param String the String
     * @param errorCode the error code
     * @throws RestException the rest exception
     */
    public static void notNullOrEmpty(String object,
    		ErrorCode error) throws RestException {
		if (object == null || object.trim().length()==0) {
			throw new RestException(error);
		}
	}
    
    /**
     * @param object
     * @param languageCode
     * @throws RestException
     */
    public static void validateString(String inputString) throws RestException {
        if (inputString == null ||inputString.equals("")) {
			throw new RestException(ErrorCode.MISSING_PARAMETER);
		}       
        
    }
    /**
     * ENum TRansport Mode check - Used to check the enum value  is empty/null and pattern
     *
     * @param String the String
     * @param errorCode the error code
     * @throws RestException the rest exception
     */



//	public static void validateEnum(
//			Class<?> enumType,
//			Object object,
//			ErrorCodeDescription errorCode) throws RestException {
//
//		("Validate Enum Called....");
//		boolean flag = false;
//
//		for(Object obj : enumType.getEnumConstants()) {
//			("Comparing " + obj + " and " + object);
//			if(obj.equals(object)) {
//				("Equals " + object);
//				flag = true;
//				break;
//			}
//		}
//		
//		if(flag==false) {
//			("Not Equals " + object);
//			throw new RestException(errorCode, errorCode.getErrorCode() + " " + errorCode.getErrorDescription());
//		}
//	}


    public static void validateLength(Object object, int min, int max, ErrorCode error) throws RestException  {

		if(object.toString().length() > max) {
			throw new RestException(error);
		}
		
		if(object.toString().length() < min){
		    throw new RestException(error);
		}

    }	
	
	public static void validateEnum(
			Class<?> enumType,
			Object object,
			ErrorCode errorCode) throws RestException {
		boolean flag = false;
		
		if(object!=null){
		for(Object obj : enumType.getEnumConstants()) {
			if(obj.equals(object)) {
				flag = true;
				break;
			}
		}
		}
		if(flag==false) {
		    throw new RestException(errorCode);
		}
	}
	
	/**
     * Date Check
     *
     * @param Objet
     * @throws RestException the rest exception
     */
    public static void checkDate(Date input, ErrorCode errorCode) throws RestException {
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	if(!sdf.format(date).equals(sdf.format(input))){
    		throw new RestException(errorCode);
    	}
    	
    }
    /**
     * Date Not null.
     *
     * @param object the object
     * @param errorCode the error code
     * @throws RestException the rest exception
     */
    public static void datenotNull(Date date, ErrorCode errorCode) throws RestException {
        if (date == null) {
			throw new RestException(errorCode);
		}
    }
    
    /**
     * Date not empty.
     *
     * @param object the object
     * @param errorCode the error code
     * @throws RestException the rest exception
     */
    public static void dateEmpty(Date date, ErrorCode errorCode) throws RestException {
        if (date.equals("")) {
			throw new RestException(errorCode);
		}
    }
    
/**Check Integer and Decimal Length*/
	
	public static void checkDecimalLength(Double value,
			ErrorCode errorCode) {
	
		String text = Double.toString(Math.abs(value));
		int integerPlaces = text.indexOf('.');
		int decimalPlaces = text.length() - integerPlaces - 1;
		
		if(integerPlaces>5 ||decimalPlaces>6 ){
			
			throw new RestException(errorCode);
			
		}
		
		
		
	}


	
	/**
	 
	 NOn -zero Validation
	 */
	public static void nonZero(Double value,
			ErrorCode errorCode)throws RestException {
		
		if(value==0){
			
			throw new RestException(errorCode);
			
		}
		
		
	}
	
	
	/**
	 
	 NOn -zero Validation
	 */
	public static void nonZero(Integer value,
			ErrorCode errorCode)throws RestException {
		
		if(value==0){
			
			throw new RestException(errorCode);
			
		}
		
		
	}
		
	
	public static void isValidEmail(String enteredEmail,ErrorCode errorCode) throws RestException {
	    			log.info("enteredEmail:"+enteredEmail);
	    	        String EMAIL_REGIX = "^[\\\\w!#$%&â€™*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&â€™*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
	    	        Pattern pattern = Pattern.compile(EMAIL_REGIX);
	    	        Matcher matcher = pattern.matcher(enteredEmail);
	    	        if(!enteredEmail.isEmpty() && enteredEmail!=null && matcher.matches())
	    	        {
	    	        	throw new RestException(errorCode);
	    	        }
	    	    }
	
	
	
	
	
	
	   public static void logicalError(Object object, ErrorCode error) throws RestException {
	     
				throw new RestException(error);
			
	    }

	
	
	
	
	
	
	
	
}
