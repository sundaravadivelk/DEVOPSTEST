package com.banking.service;


import com.banking.beans.Account;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InsufficientInitialAmountException;
import com.banking.exceptions.InvalidAccountNumberException;

public interface AccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException;
	public boolean depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException;
	public int withdrawlAmount(int accountNumber,int amount) throws InvalidAccountNumberException,InsufficientBalanceException;


}