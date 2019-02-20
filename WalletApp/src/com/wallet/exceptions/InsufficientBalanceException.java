package com.wallet.exceptions;
@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception {

	@Override
	public String getMessage() {

		return "Insufficient Balance!";
	}
	
}