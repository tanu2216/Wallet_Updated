package com.wallet.AccountService;

import java.math.BigDecimal;

import com.wallet.beans.Customer;
import com.wallet.beans.Wallet;
import com.wallet.exceptions.*;

public interface AccountService {
	
	
	Customer createAccount(String phoneNumber,String name, Wallet wallet) throws NumberaAlreadyExistsException;
	
	String Deposit(String phoneNumber,BigDecimal amount)throws MobileNumberDoesNotExistException;
	
	String withdraw(String phoneNumber,BigDecimal amount)throws MobileNumberDoesNotExistException, InsufficientBalanceException;
	
	Customer Show(String phoneNumber) throws MobileNumberDoesNotExistException;
	

}