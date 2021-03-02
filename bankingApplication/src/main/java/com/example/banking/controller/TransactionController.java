package com.example.banking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.dto.CreateAccount;
import com.example.banking.dto.TransactionDTO;
import com.example.banking.exception.LowBalanceException;
import com.example.banking.helper.BankingHelper;
import com.example.banking.model.CreateAccountDetails;
import com.example.banking.repository.CreateAccountRepository;
import com.example.banking.repository.TransactionsRepository;
import com.example.banking.services.TransactionsServices;
import com.example.banking.model.Transaction;


@RestController
public class TransactionController {
	@Autowired
	public CreateAccountRepository createAccountRepository;//use user account before submitting
	
	@Autowired
	public TransactionsServices transactionService;
	
	@Autowired
	public BankingHelper bankingHelper;
	@Autowired
	public TransactionsRepository transRepo;
	
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value="/allTransactions/{accNo}/{days}")
	public List<TransactionDTO> getAllTransactionsWithLimits(@PathVariable String accNo,@PathVariable("days") Integer noOfTransactions) {
		List<TransactionDTO> transactionList=new ArrayList<>();
		try {
			if((accNo!=null && !accNo.equalsIgnoreCase("")) || noOfTransactions!=null) {
				transactionList= transactionService.findAllTransactionWithAccNoAndDays(accNo,noOfTransactions);
			}else {
				System.out.println("Account number is null, Cannot fetch transaction details!");
			}
		 }catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return transactionList;
	}
	
	
	
@PostMapping(value="/transaction")
public void accountTransaction(@RequestBody TransactionDTO transaction) throws LowBalanceException{
	//CreateAccountDetails createAccountDetails;
	try {
	if(transaction!=null && !transaction.equals("")) {
		Transaction transactionModel=bankingHelper.convertToTransactionModel(transaction);
		if(transaction.getFromAccType().equalsIgnoreCase("Savings")) {
			 makeTransaction(transactionModel,transaction);
		}else if(transaction.getFromAccType().equalsIgnoreCase("Current")) {
			 makeTransaction(transactionModel,transaction);
		}
	}
	}catch(LowBalanceException e) {
		System.out.println(e.getMessage());
		}
	}


public void makeTransaction(Transaction transactionModel,TransactionDTO transactionDto) throws LowBalanceException{
	
	CreateAccountDetails createAccountDetails= new CreateAccountDetails();
	try {
	createAccountDetails=transactionService.findDetailsByCustomerAccountNo(transactionDto.getFromAccNo());
//	createAccountDetails=createAccountRepository.findByCustomerAccountNo(transactionDto.getFromAccNo());
	if(createAccountDetails.getBalance()>=5000 && createAccountDetails.getAccountType().equalsIgnoreCase("Savings")) {
		Long newBalance=Long.valueOf(createAccountDetails.getBalance())+Long.valueOf(transactionDto.getAmount());
		//add amount to the user
		createAccountDetails.setBalance(Long.valueOf(newBalance));
		//createAccountDetails=transactionService.saveOrUpdateAccount(createAccountDetails);
		//createAccountDetails=createAccountRepository.save(createAccountDetails);
		//make transaction
		//transactionModel=transactionService.saveOrUpdateTransaction(transactionModel);
		//transactionModel=transRepo.save(transactionModel);
		System.err.println("Transaction Completed successfully for Imps of "+transactionDto.getFromAccType()+" account!");
	}else if(createAccountDetails.getBalance()>=10000 && createAccountDetails.getAccountType().equalsIgnoreCase("Current")){
		Long newBalance=Long.valueOf(createAccountDetails.getBalance())+Long.valueOf(transactionDto.getAmount());
		//add amount to the user
		createAccountDetails.setBalance(Long.valueOf(newBalance));
		//createAccountDetails=transactionService.saveOrUpdateAccount(createAccountDetails);
		//createAccountDetails=createAccountRepository.save(createAccountDetails);
		//make transaction
		//transactionModel=transactionService.saveOrUpdateTransaction(transactionModel);
		//transactionModel=transRepo.save(transactionModel);
		System.err.println("Transaction Completed successfully for Imps of "+transactionDto.getFromAccType()+" account!");
	}else{
		//lock the account if not locked
		CreateAccountDetails details = new CreateAccountDetails();
		if(createAccountDetails.getStatus().equalsIgnoreCase("Locked")) {
			System.err.println("Account is already locked");
		}else {
			details.setStatus("Locked");
			System.out.println("Account Locked and updated, Since balance is low than minimum!");
			//createAccountDetails=transactionService.saveOrUpdateAccount(details);
			//createAccountDetails=createAccountRepository.save(details);
		}
		
		throw new LowBalanceException("Cant make any transactions due to low balance in the account");
	}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
}








/*@PostMapping(value="/transaction")
public void accountTransaction(@RequestBody TransactionDTO transaction) throws LowBalanceException{
	//CreateAccountDetails createAccountDetails;
	if(transaction!=null && !transaction.equals("")) {
		Transaction transactionModel=bankingHelper.convertToTransactionModel(transaction);
		
		if(transaction.getFromAccType().equalsIgnoreCase("Savings")) {
			
			 makeTransaction(transactionModel,transaction);
			//check for balance
			createAccountDetails=createAccountRepository.findByCustomerAccountNo(transaction.getFromAccNo());
			if(createAccountDetails.getBalance()>=5000) {
				String newBalance=createAccountDetails.getBalance()+transaction.getAmount();
				//add amount to the user
				createAccountDetails.setBalance(Long.valueOf(newBalance));
				createAccountDetails=createAccountRepository.save(createAccountDetails);
				//make transaction
				transactionModel=transRepo.save(transactionModel);
				System.out.println("Transaction Completed successfully for Imps of savings account!");
			}else {
				//lock the account if not locked
				CreateAccountDetails details = new CreateAccountDetails();
				if(createAccountDetails.getStatus().equalsIgnoreCase("Locked")) {
					System.out.println("Account is already locked");
				}else {
					details.setStatus("Locked");
					createAccountDetails=createAccountRepository.save(details);
				}
				
				throw new LowBalanceException("Cant make any transactions due to low balance in the account");
			}
		}else if(transaction.getFromAccType().equalsIgnoreCase("Current")) {
			
			 makeTransaction(transactionModel,transaction);
			//check for balance
			createAccountDetails=createAccountRepository.findByCustomerAccountNo(transaction.getFromAccNo());
			if(createAccountDetails.getBalance()>=10000) {
				String newBalance=createAccountDetails.getBalance()+transaction.getAmount();
				//add amount to the user
				createAccountDetails.setBalance(Long.valueOf(newBalance));
				createAccountDetails=createAccountRepository.save(createAccountDetails);
				//make transaction
				transactionModel=transRepo.save(transactionModel);
				System.out.println("Transaction Completed successfully for Imps current account");
			}else {
				//lock the account if not locked
				CreateAccountDetails details = new CreateAccountDetails();
				if(createAccountDetails.getStatus().equalsIgnoreCase("Locked")) {
					System.out.println("Account is already locked");
				}else {
					details.setStatus("Locked");
					createAccountDetails=createAccountRepository.save(details);
				}
				
				throw new LowBalanceException("Cant make any transactions due to low balance in the account");
			}
		}
	}*/
}



