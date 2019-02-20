package com.wallet.Client;
import java.math.BigDecimal;
import java.util.Scanner;

import com.wallet.AccountService.AccountServiceImpl;
import com.wallet.accountRepo.AccountRepoImpl;
import com.wallet.beans.Customer;
import com.wallet.beans.Wallet;
import com.wallet.exceptions.InsufficientBalanceException;
import com.wallet.exceptions.MobileNumberDoesNotExistException;
import com.wallet.exceptions.NumberaAlreadyExistsException;



public class ClientView {
	static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) {

		AccountRepoImpl accountrepoimpl= new AccountRepoImpl();
		AccountServiceImpl accountserviceimpl= new AccountServiceImpl(accountrepoimpl);
		
		int choice;
		while (true) {
			System.out.println("Enter your choice: ");
			System.out.println("1. Add wallet Account");
			System.out.println("2. Show Balance");
			System.out.println("3. Withdraw from wallet Account");
			System.out.println("4. Deposit into Wallet Account");
			System.out.println("5. Transfer funds to another account");
			System.out.println("6. exit");
			choice= sc.nextInt();
			
			switch (choice) {
			
			case 1: System.out.println("Enter your mobile number: ");
					String mobile=sc.next();
					verifyMobile(mobile);
					System.out.println("Enter your name: ");
					String name= sc.next();
					//verifyName(name);
					sc.nextLine();
					System.out.println("Enter initial balance: ");
					BigDecimal balance= sc.nextBigDecimal();
					verifyNegativeBalance(balance);
						Wallet wallet= new Wallet();
						wallet.setBalance(balance);
						
					try {
						Customer customer=accountserviceimpl.createAccount(mobile,name,wallet);
						if(customer!=null)
							System.out.println("Customer added Successfully!!");
					} catch (NumberaAlreadyExistsException e1) {
						System.out.println(e1.getMessage());
					}					
					break;
					
					
			case 2: System.out.println("Enter your mobile number: ");
					String number= sc.next();
					sc.nextLine();
				try {
					Customer cst= accountserviceimpl.Show(number);
					System.out.println(cst);
					
					
				} catch (MobileNumberDoesNotExistException e) {
					System.out.println(e.getMessage());
				}
				break;
			
					
			
			case 3: System.out.println("Enter your mobile number: ");
					String phone= sc.next();
					sc.nextLine();
						System.out.println("Enter the amount to withdraw: ");
							BigDecimal withdrawAmount= sc.nextBigDecimal();
					try {
						String result=accountserviceimpl.withdraw(phone,withdrawAmount);
						System.out.println(result);
					}
					catch(MobileNumberDoesNotExistException ian) {
						System.out.println(ian.getMessage());
					} catch (InsufficientBalanceException e) {
						
						System.out.println(e.getMessage());
						
					}	
					break;
					
			case 4: System.out.println("Enter your mobile number: ");
					String mob= sc.next();
					sc.nextLine();
					System.out.println("Enter the amount to deposit: ");
					BigDecimal amount= sc.nextBigDecimal();
					try {	
						String result=accountserviceimpl.Deposit(mob,amount);
						System.out.println(result);
					}
					catch(MobileNumberDoesNotExistException ian) {
						System.out.println(ian.getMessage());
					}
					break;
					
			case 5: System.out.println("Enter the source mobile number");
					String source= sc.next();
					sc.nextLine();
					System.out.println("Enter the destination mobile number: ");
					String dest= sc.next();
					sc.nextLine();
					System.out.println("Enter the amount to transfer: ");
					BigDecimal transferAmount= sc.nextBigDecimal();
					sc.nextLine();
					try {
						String result=accountserviceimpl.fundsTransfer(source, dest,transferAmount);
						System.out.println(result);
					}
					catch(MobileNumberDoesNotExistException ian) {
						System.out.println(ian.getMessage());
					}
					catch(InsufficientBalanceException ib) {
						System.out.println(ib.getMessage());
					}
					break;
					
			case 6: System.exit(0);
			
			}
			
			
		}
		
	}

	private static void verifyNegativeBalance(BigDecimal balance) {
		if(balance.compareTo(new BigDecimal(0))<0) {
			BigDecimal bal= sc.nextBigDecimal();
			verifyNegativeBalance(bal);
			
		}
		
		
	}

	private static void verifyName(String name) {
		if(!name.matches(" [a-zA-Z]{2,}")) {
			System.out.println("Please enter a valid name: ");
			String nam= sc.next();
			verifyName(nam);
		}
		
	}

	private static boolean verifyMobile(String mobile) {
		boolean b=true;
		if(!mobile.matches("[0-9]+")) {
			System.out.println("This is not a valid mobile number \n Please enter correct mobile number:");
			String mob=sc.nextLine();
			verifyMobile(mob);
		}
		if(mobile.length()==10)
			return true;
		else {
			System.out.print("Mobile number length is less that 10 \n Please enter correct mobile number: ");
			sc.nextLine();
			String mobi=sc.next();
			verifyMobile(mobi);
			}
		return b;
		
		
	}
	

}