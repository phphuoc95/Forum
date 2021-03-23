package com.nobj.forum.exception;

public class CustomApiRequestException extends RuntimeException {
	
	private static final long serialVersionUID = -2078632414281065771L;

	public CustomApiRequestException(String message) {
		super(message);
	}
	
	public CustomApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
