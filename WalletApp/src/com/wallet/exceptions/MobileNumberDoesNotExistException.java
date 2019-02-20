package com.wallet.exceptions;
@SuppressWarnings("serial")
public class MobileNumberDoesNotExistException extends Exception {
	
	
	@Override
	public String getMessage() {
	
		return "Invalid Mobile Number";
		
	}

	
}