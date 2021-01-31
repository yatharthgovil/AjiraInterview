package com.ajira.exceptions;

public class InvalidConnectionException extends AbstractCommandException{

	public InvalidConnectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	private int errorCode = 52372;

	public int getErrorCode() {
		return errorCode;
	}
	
	
}
