package com.ajira.exceptions;

public class InvalidCommandSyntaxException extends AbstractCommandException{

	public InvalidCommandSyntaxException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	private final int errorCode = 42535;
	
	public int getErrorCode() {
		return errorCode;
	}
	}
