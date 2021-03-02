package com.example.banking.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.banking.dto.NewAccountRequest;
import com.example.banking.model.Account;
import com.example.banking.model.AccountType;
import com.example.banking.model.Customer;


public interface AccountService {
	
	long createNewAccount(NewAccountRequest accountDetails);
	
	Customer getCustomerDetailsById(Long customerId);
	
	void validateMinimumBalance(AccountType accountType, double depositAmount);

	Page<Account> getAllAccounts(Pageable pageable);
	
	Account addInterestToAccount(Account account, double interest);

}
