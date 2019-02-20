package com.wallet.accountRepo;

import com.wallet.exceptions.*;
import com.wallet.beans.Customer;

public interface AccountRepo {
	
	boolean save (Customer customer) throws NumberaAlreadyExistsException;
	
	Customer searchAccount(String phoneNumber) throws MobileNumberDoesNotExistException;

}