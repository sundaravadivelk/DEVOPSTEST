package com.banking.service;


import com.banking.beans.Account;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InsufficientInitialAmountException;
import com.banking.exceptions.InvalidAccountNumberException;
import com.banking.repositary.AccountRepository;

public class AccountServiceImpl implements AccountService {
	AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	@Override
	public Account createAccount(int accountNumber,int amount)throws InsufficientInitialAmountException
	{
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		
		if(accountRepository.save(account))
		{
			return account;
		}
		
		return null;
			}
	@Override
	public boolean depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException {
		
		int depositedAmount;
		Account acc=accountRepository.searchAccount(accountNumber);		
		
		if(acc==null)
		{
			throw new InvalidAccountNumberException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		//logic for storing the deposit amount..
		depositedAmount=account.getAmount();
		return true;
	}
	@Override
	public int withdrawlAmount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException {
		
		int withdrawnAmount;
		accountRepository.searchAccount(accountNumber);	
		
			
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		//logic for storing the Withdraw amount..
		withdrawnAmount=account.getAmount();
				
		return withdrawnAmount;
		
	}
	
	
	

}
