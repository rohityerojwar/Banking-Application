package com.myProject.banking.service;

import java.util.List;

import com.myProject.banking.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);

	
	AccountDto getAccountById(long id);
	

	AccountDto deposit(long id, double amount);
	
	
	AccountDto witdraw(long id, double amount);


	List<AccountDto> getAllAccounts();
	
	
	void deleteAccount(long id);



}
