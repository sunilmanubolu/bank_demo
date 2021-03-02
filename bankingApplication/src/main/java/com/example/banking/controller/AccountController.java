package com.example.banking.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.dto.CreateAccount;
import com.example.banking.exception.LowBalanceException;
import com.example.banking.helper.BankingEnum;
import com.example.banking.helper.BankingHelper;
import com.example.banking.repository.CreateAccountRepository;
import com.example.banking.services.AccountService;
import com.example.banking.services.BankService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {
	
	@Autowired
	public CreateAccountRepository createAccountRepository;
	@Autowired
	public BankingHelper bankingHelper;
	@Autowired
	public BankService bankService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello";
	}
	
	
	/*@PostMapping
	public ResponseEntity<Object> createNewAccount(@Valid @RequestBody NewAccountRequest accountDetails){
		
	}*/
	//Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@SuppressWarnings("unlikely-arg-type")
	@PostMapping(value="/createCustomerAccount")
	public ResponseEntity<Object> createAccount(@RequestBody CreateAccount createCustomerAccount) throws LowBalanceException {
		//CreateAccountDetails accDetails = new CreateAccountDetails();
		try {
		if(createCustomerAccount!=null && !createCustomerAccount.equals("")) {
			//long accountNumber = accountService.createNewAccount(createCustomerAccount);//imp
			
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
		
	}
	
	
	@GetMapping("/getAcc")
	public List<CreateAccount> getAcc() {
		List<CreateAccount> createAccount = new ArrayList<>();
		//createAccountRepository.findAll();
		return this.getListOfUsers();
		
	}
	
	/*@GetMapping(value="getCustomerByAccNo")
	public CreateAccount getCustomerByAccNo(@PathVariable String accountNumber) {
		if(accountNumber!=null && accountNumber.equals("")) {
			
		}
		return null;
		
	}*/
	
	
	public List<CreateAccount> getListOfUsers(){
		List<CreateAccount> listOfUsers= new ArrayList<>();
		listOfUsers.add(new CreateAccount(1, "Amrutha", "Kanthimath", "amruthaKanthimath", "1000005050", "9482929767", "abcd abcd", "Savings", "amrutha.kanthimath@gmail.com", 100000, "BVYPA5458A", "123456789A"));
		listOfUsers.add(new CreateAccount(2, "Anitha", "Sharma", "anithaSharma", "1000005051", "9482929778", "abcd abcd", "Current", "anitha.sharma@gmail.com", 100000, "BVYPA5459B", "1234567abA"));
		listOfUsers.add(new CreateAccount(3, "Anusha", "Hiremath", "aushaHiremath", "1000005052", "9482929789", "abcd abcd", "Savings", "ausha.hiremath@gmail.com", 100000, "BVYPA5456B", "1234567bvA"));
		listOfUsers.add(new CreateAccount(4, "Avinash", "Sharma", "avinashSharma", "1000005053", "9482929790", "abcd abcd", "Current", "avinash.sharma@gmail.com", 100000, "BVYPA5457B", "1234567mnA"));
		listOfUsers.add(new CreateAccount(5, "Bhavana", "Prabhu", "bhavanaPrabhu", "1000005054", "9482929780", "abcd abcd", "Savings", "bhavana.prabhu@gmail.com", 100000, "BVYPA5454B", "1234567uyA"));
		return listOfUsers;
		
		
	}
	
/*	
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerDetails customerDetails,
			@PathVariable Long customerNumber) {

		return bankingService.updateCustomer(customerDetails, customerNumber);
	}

	
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long customerNumber) {

		return bankingService.deleteCustomer(customerNumber);
	}*/

	
	
}
