package com.online.movie.ticket.exception;


import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;


import com.online.movie.ticket.core.dto.BaseDto;

import lombok.extern.log4j.Log4j2;



/***
 * unified exception handling throughout a whole application.
 * 
 * Spring 3.2 brings support for a global @ExceptionHandler with the @ControllerAdvice annotation. 
 * This enables a mechanism that breaks away from the older MVC model and makes use of ResponseEntity along with the type safety 
 * and flexibility of @ExceptionHandler:
 *
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler
{

	


	
	
	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public BaseDto HandleNotReadableException(HttpServletRequest request, Exception ex){
		log.debug("HandleNotReadableException"+ex.getMessage());
		BaseDto respDto = new BaseDto();
		respDto.setResponse(ErrorCode.INVALID_REQUEST_METHOD);
		respDto.setResponseObject(this.getClass().toString()+"-->HandleNotReadableException==>"+ex.getMessage());
		respDto.setRequestURL(request.getRequestURL());
		respDto.setResponseDescription(respDto.getResponseDescription()+"/"+ex.getMessage());
		return respDto;
	}
	
	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public BaseDto HandleMethodNotSupportedException(HttpServletRequest request, Exception ex){
		log.debug("HttpRequestMethodNotSupportedException"+ex.getMessage());
		BaseDto respDto = new BaseDto();
		respDto.setResponse(ErrorCode.NOT_READABLE_API_INPUT_PARAMETER);
		respDto.setResponseObject(this.getClass().toString()+"-->HandleNotReadableException==>"+ex.getMessage());
		respDto.setRequestURL(request.getRequestURL());
		respDto.setResponseDescription(respDto.getResponseDescription()+"/"+ex.getMessage());
		return respDto;
	}
	
	
	
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseDto HandleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex){
		log.debug("HandleMethodArgumentNotValidException"+ex.getMessage());
		BaseDto respDto = new BaseDto();
		respDto.setResponse(ErrorCode.INVALID_REQUEST_ARUGUMENT_TO_API);
		
		List<String> objectDetail = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getObjectName()+"."+x.getField()).collect(Collectors.toList());

		List<String> defaultMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		if(objectDetail.size() > 0)
		{
			MethodArgument a []= new MethodArgument[objectDetail.size()];
			if (objectDetail.size() == defaultMessage.size())
			{
				for (int i=0;i<objectDetail.size();i++)
				{
					a [i] = new MethodArgument(objectDetail.get(i),defaultMessage.get(i));
				}
			}
			respDto.setResponseObject(a);
		}
		respDto.setRequestURL(request.getRequestURL());
		respDto.setResponseDescription(respDto.getResponseDescription()+"/"+ex.getMessage());
		return respDto;
	}
	

class MethodArgument
{	

	String fieldName;
	String errorMessage;
	
	MethodArgument(String fieldName, String errorMessage) {
		this.fieldName = fieldName;
		this.errorMessage = errorMessage;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
}

	
	
	
}
