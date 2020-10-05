package com.online.movie.ticket.exception;

import com.online.movie.ticket.exception.ErrorCode;
/**
 * The Class RestException.
 */
public class RestException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5621993860252119284L;

	/** The message. */
	private ErrorCode error;

	/**
	 * Instantiates a new rest exception.
	 *
	 * @param message
	 *            the message
	 */
	public RestException(ErrorCode error) {
		super(error.getDescription());
		this.setError(error);
		
	}

	
	@Override
	public String toString() {
		return "RestException [ message=" + error + "]";
	}

	public ErrorCode getError() {
		return error;
	}

	public void setError(ErrorCode error) {
		this.error = error;
	}

}
