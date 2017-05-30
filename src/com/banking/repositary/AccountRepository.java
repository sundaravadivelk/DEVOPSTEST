package com.banking.repositary;

import com.banking.beans.Account;

public interface AccountRepository {
	
	boolean save(Account account);
	
	Account searchAccount(int accountNumber);
	
	

}
