package com.ajira.exceptions;

public class InvalidNodeException extends AbstractCommandException {

	private final int errorCode = 76125;

	public InvalidNodeException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

	public int getErrorCode() {
		return errorCode;
	}

	
	
}
