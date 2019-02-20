package com.wallet.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.wallet.AccountService.AccountService;
import com.wallet.AccountService.AccountServiceImpl;
import com.wallet.accountRepo.AccountRepo;
import com.wallet.accountRepo.AccountRepoImpl;
import com.wallet.beans.Customer;
import com.wallet.beans.Wallet;
import com.wallet.exceptions.InsufficientBalanceException;
import com.wallet.exceptions.MobileNumberDoesNotExistException;
import com.wallet.exceptions.NumberaAlreadyExistsException;

public class WalletTest {

	Customer account = new Customer();
	Customer account2 = new Customer();
	AccountRepo accountRepoImpl= new AccountRepoImpl();
	AccountService accountServiceimpl= new AccountServiceImpl(accountRepoImpl);
	

	@Before
	public void setUp() throws Exception {
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal(500));
		account=accountServiceimpl.createAccount("112","Sagar", wallet);
		
		
		
	}
	
	
	/* create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
//	@Test
//	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws NumberaAlreadyExistsException
//	{
//		Wallet wallet2 = new Wallet();
//		wallet2.setBalance(new BigDecimal(500));
//		when(accountServiceimpl.createAccount("130", "Sagar", wallet2)).then true);
//		assertEquals(true,accountServiceimpl.createAccount("130","Sagar", wallet2));
//	}
//	
	@Test(expected=com.wallet.exceptions.NumberaAlreadyExistsException.class)
	
	public void whenSameNumberIsUsedToCreateMultiPleAccounts() throws NumberaAlreadyExistsException {
		
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal(500) );
		accountServiceimpl.createAccount("112", "Sagar", wallet);	
	}
	
	
	@Test(expected=com.wallet.exceptions.MobileNumberDoesNotExistException.class)
	
  public void whenInvalidAccountNumberIsPassedForDeposit() throws MobileNumberDoesNotExistException, NumberaAlreadyExistsException{
		
		accountServiceimpl.Deposit("105", new BigDecimal(2000));
		
	}
	
@Test
	public void whenValidAccountNumberIsPassedSystemShouldDepositMoney() throws MobileNumberDoesNotExistException, NumberaAlreadyExistsException{
	
		String str="Balance after Deposit: "+(account.getWallet().getBalance().add(new BigDecimal(2000)));
		assertEquals(str,accountServiceimpl.Deposit("112", new BigDecimal(2000)));
	}
	
	@Test(expected=com.wallet.exceptions.InsufficientBalanceException.class)
	
	public void whenBalanceIsNotSufficientSystemShouldThrowException() throws InsufficientBalanceException, MobileNumberDoesNotExistException, NumberaAlreadyExistsException{
		
		accountServiceimpl.withdraw("112",new BigDecimal(1500));
		
	}
	
	@Test
	public void whenBalanceIsSufficientAndAccountNumberIsCorrectSysytemShouldDeductMoney() throws MobileNumberDoesNotExistException, InsufficientBalanceException, NumberaAlreadyExistsException{
		
		String str = "Balance after deduction: "+account.getWallet().getBalance().subtract(new BigDecimal(500));
		assertEquals(str,accountServiceimpl.withdraw("112",new BigDecimal(500)));
	
	}	
//	@Test
//	public void when
}
