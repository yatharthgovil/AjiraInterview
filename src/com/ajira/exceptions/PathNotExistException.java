package com.ajira.exceptions;

public class PathNotExistException extends AbstractCommandException {

	public PathNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	private int errorCode = 73782;
	public int getErrorCode() {
		return errorCode;
	}
}

