package com.myProject.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myProject.banking.dto.AccountDto;
import com.myProject.banking.entity.Account;
import com.myProject.banking.mapper.AccountMapper;
import com.myProject.banking.repository.AccountRepository;
import com.myProject.banking.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService{

	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	@Override
	public AccountDto getAccountById(long id) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}
	
	@Override
	public AccountDto deposit(long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}
	
	@Override
	public AccountDto witdraw(long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	public List<AccountDto> getAllAccounts(){
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}
	
	@Override
	public void deleteAccount(long id) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		accountRepository.deleteById(id);
	}
}
