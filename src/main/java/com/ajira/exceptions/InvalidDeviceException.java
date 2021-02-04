package com.ajira.exceptions;

public class InvalidDeviceException extends AbstractCommandException {

	public InvalidDeviceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	private int errorCode = 38472;
	public int getErrorCode() {
		return errorCode;
	}
}
