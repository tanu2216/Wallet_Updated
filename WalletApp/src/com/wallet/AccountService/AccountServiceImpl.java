package com.wallet.AccountService;


import java.math.BigDecimal;

import com.wallet.accountRepo.AccountRepo;
import com.wallet.accountRepo.AccountRepoImpl;
import com.wallet.beans.Customer;
import com.wallet.beans.Wallet;
import com.wallet.exceptions.InsufficientBalanceException;
import com.wallet.exceptions.MobileNumberDoesNotExistException;
import com.wallet.exceptions.NumberaAlreadyExistsException;

public class AccountServiceImpl implements AccountService {

	AccountRepo accountrepoImpl;
	public AccountServiceImpl(AccountRepo accountRepoImpl2) {
	
		super();
		this.accountrepoImpl=accountRepoImpl2;
	}
	
	@Override
	public Customer createAccount(String phoneNumber,String name, Wallet wallet)throws NumberaAlreadyExistsException{
		
		Customer customer = new Customer();
		customer.setMobileNumber(phoneNumber);
		customer.setWallet(wallet);
		customer.setName(name);
		if(accountrepoImpl.save(customer))
		{
			System.out.println("Done");
			return customer;
		}
		return null;
	}

	@Override
	public String Deposit(String accountNumber, BigDecimal amount) throws MobileNumberDoesNotExistException {
		
			Customer customer = accountrepoImpl.searchAccount(accountNumber);
			
			customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
			
			return "Balance after Deposit: "+customer.getWallet().getBalance();
			
	}

	@Override
	public String withdraw(String phoneNumber, BigDecimal amount) throws MobileNumberDoesNotExistException,InsufficientBalanceException {
	
		Customer account = accountrepoImpl.searchAccount(phoneNumber);
		
		if((account.getWallet().getBalance().subtract(amount).compareTo(new BigDecimal(0)))<0) {
			throw new InsufficientBalanceException();
		}
		
			BigDecimal bal= account.getWallet().getBalance();
			account.getWallet().setBalance(bal.subtract(amount));
			return "Balance after deduction: "+account.getWallet().getBalance();
			//return true;
			
		
	}

	public String fundsTransfer(String ac1, String ac2, BigDecimal amount) throws MobileNumberDoesNotExistException,InsufficientBalanceException {
		Customer cs1=accountrepoImpl.searchAccount(ac1);
		Customer cs2=accountrepoImpl.searchAccount(ac2);
		if(cs1.getWallet().getBalance().compareTo(amount)>=0) {
			BigDecimal source=cs1.getWallet().getBalance();
			BigDecimal dest=cs2.getWallet().getBalance();
			cs1.getWallet().setBalance(source.subtract(amount));
			cs2.getWallet().setBalance(dest.add(amount));
			return "Balance after deduction and transfer respectively: "+cs1.getWallet().getBalance()+" "+cs2.getWallet().getBalance();
		}
		return ac2;

	}

	@Override
	public Customer Show(String phoneNumber) throws MobileNumberDoesNotExistException {
		
		Customer account = accountrepoImpl.searchAccount(phoneNumber);
		
		return account;
	}

}
