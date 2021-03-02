package com.example.banking.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.dto.CreateAccount;
import com.example.banking.exception.LowBalanceException;
import com.example.banking.helper.BankingHelper;
import com.example.banking.repository.CreateAccountRepository;
import com.example.banking.services.AccountService;
import com.example.banking.services.BankService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/accounts")
@Validated
public class AccountsController {
	
	@Autowired
	private AccountService accountService;
	
	
	@Autowired
	public CreateAccountRepository createAccountRepository;
	@Autowired
	public BankingHelper bankingHelper;
	@Autowired
	public BankService bankService;
	
	/*@Autowired
	private AccountService accountService;*/
	
	
	/*@PostMapping({ "", "/" })
	public ResponseEntity<Object> createNewAccount(@Valid @RequestBody NewAccountRequest accountDetails) {
		long accountNumber = accountService.createNewAccount(accountDetails);
		log.info("account number assigned : {}", accountNumber);
		HashMap<String, String> data = new HashMap<>();
		data.put("accountNumber", String.valueOf(accountNumber));
		return ResponseEntity.ok(data);
	}*/
	
	
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping(value="/createCustomerAccount")
	public ResponseEntity<Object> createAccount(@RequestBody CreateAccount createCustomerAccount) throws LowBalanceException {
		
		if(createCustomerAccount!=null && !createCustomerAccount.equals("")) {
			long accountNumber = bankService.createNewAccount(createCustomerAccount);//imp
			log.info("account number assigned : {}", accountNumber);
			if(accountNumber!=0) {
				HashMap<String, String> data = new HashMap<>();
				data.put("accountNumber", String.valueOf(accountNumber));
				return ResponseEntity.ok(data);
			}
			
			}
			
			
		return  ResponseEntity.status(HttpStatus.OK).body("New Customer cannot be created.");
		
	
	}
	

}
