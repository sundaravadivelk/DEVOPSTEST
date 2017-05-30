package com.banking.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.beans.Account;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InsufficientInitialAmountException;
import com.banking.exceptions.InvalidAccountNumberException;
import com.banking.repositary.AccountRepository;
import com.banking.service.AccountService;
import com.banking.service.AccountServiceImpl;


public class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;
	
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService=new AccountServiceImpl(accountRepository);
		
	}
	
	/*
	 * test cases for create account
	 * 1. when the amount is less than 500 system should generate exception
	 * 2. when the valid(101,5000) info is passed account should be created successfully
	 */
	
	
	@Test(expected=com.banking.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsufficientInitialAmountException
	{
		accountService.createAccount(101,400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{    
		
		Account account = new Account();
		
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}	
	
	@Test(expected=com.banking.exceptions.InvalidAccountNumberException.class)
	public void whenAmountDeposit() throws InvalidAccountNumberException 
	{    
		
		Account account = new Account();
		
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(account, accountService.depositAmount(101, 1000));
	}	
	
	
	@Test
	public void whenAmountDepositSuccess() throws InvalidAccountNumberException 
	{    
		
		Account account = new Account();
		
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(true, accountService.depositAmount(101, 1000));
	}	
	

	@Test
	public void whenAmountWithdrawl() throws InvalidAccountNumberException, InsufficientBalanceException 
	{    
		
		Account account = new Account();
		
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(account, accountService.withdrawlAmount(101,1000));
	}
	
/*	@Test(expected=com.banking.exceptions.InsufficientBalanceException.class)
	public void whenAmountWithdrawlInsuffiencientBalance() throws  InsufficientBalanceException, InvalidAccountNumberException 
	{    
		
		Account account = new Account();
		
		account.setAccountNumber(101);
		account.setAmount(5000);		
		when(accountRepository.searchAccount(101)).thenReturn(account);
		
		assertEquals(account.getAmount(), accountService.withdrawlAmount(101,6000));
	}*/
	
}
