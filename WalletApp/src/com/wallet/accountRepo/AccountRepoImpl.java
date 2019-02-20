package com.wallet.accountRepo;


import java.util.HashMap;
import java.util.Iterator;

import com.wallet.beans.Customer;
import com.wallet.exceptions.MobileNumberDoesNotExistException;
import com.wallet.exceptions.NumberaAlreadyExistsException;

public class AccountRepoImpl implements AccountRepo {

	HashMap<String,Customer> accounts= new HashMap<String,Customer>();
	@Override
	public boolean save(Customer customer) throws NumberaAlreadyExistsException  {
			if(accounts.containsKey(customer.getMobileNumber()))
				throw new NumberaAlreadyExistsException();
			accounts.put(customer.getMobileNumber(),customer);
			return true;
		
	}

	@Override
	public Customer searchAccount(String phoneNumber) throws MobileNumberDoesNotExistException {
		
		
			Iterator<Customer> iterator =accounts.values().iterator();
			while(iterator.hasNext()) {
				Customer customer = iterator.next();
				if(customer.getMobileNumber().equals(phoneNumber)) 
					return customer;
			}
			return null;
		
	}
}