package com.example.banking.services;

import com.example.banking.model.AccountType;
import com.example.banking.model.Customer;
import com.example.banking.dto.CreateAccount;
import com.example.banking.dto.NewAccountRequest;

public interface BankService {
	
	public String addCustomer(CreateAccount createAcc);
	public CreateAccount checkForAccExistance(String emailId, String mobileNo);
	
	long createNewAccount(CreateAccount accountDetails);
	Customer getCustomerDetailsById(Long customerId);
	void validateMinimumBalance(AccountType accountType, double depositAmount);

}
