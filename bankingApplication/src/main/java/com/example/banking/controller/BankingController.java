package com.example.banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.dto.CreateAccount;
import com.example.banking.exception.LowBalanceException;

@RestController
public class BankingController {
	
	
	/*@SuppressWarnings("unlikely-arg-type")
	@PostMapping(value="/createCustomerAccount")
	public ResponseEntity<Object> createAccount(@RequestBody CreateAccount createCustomerAccount) throws LowBalanceException {
		try {
		if(createCustomerAccount!=null && !createCustomerAccount.equals("")) {
			long accountNumber = accountService.createNewAccount(createCustomerAccount);//imp
			
			if(createCustomerAccount.getAccountType().equals("Savings")) {
			if(createCustomerAccount.getBalance()>=5000) {//checking balance if its more than Zero or else 
				bankService.addCustomer(createCustomerAccount);
				return  ResponseEntity.status(HttpStatus.CREATED).body("New Customer created successfully.");
		}else {
			System.out.println("Minimum balance of 5000 for opening new account ");
			throw new LowBalanceException("Minimum balance of 5000 for opening new Savings account ");
		}
			}else if(createCustomerAccount.getAccountType().equals("Current")) {
				System.out.println("The Account type is Currnet "+"Current");
				if(createCustomerAccount.getBalance()>=10000) {
					bankService.addCustomer(createCustomerAccount);
					return  ResponseEntity.status(HttpStatus.CREATED).body("New Customer created successfully.");
				}else {
					System.out.println("Minimum balance of 10000 for opening new current account ");
					throw new LowBalanceException("Minimum balance of 1000 for opening new Current account ");
					
				}
				
			}
		}
		}catch(LowBalanceException e) {
			
			System.out.println(e.getMessage());
		}
		return  ResponseEntity.status(HttpStatus.OK).body("New Customer cannot be created successfully.");
}*/	
}
