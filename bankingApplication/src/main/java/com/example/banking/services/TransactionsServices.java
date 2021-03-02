package com.example.banking.services;

import java.util.List;

import com.example.banking.dto.TransactionDTO;
import com.example.banking.model.CreateAccountDetails;
import com.example.banking.model.Transaction;

public interface TransactionsServices {
	public void transaferAmount();
	public List<TransactionDTO> findAllTransactionWithAccNoAndDays(String accNo,Integer noOfTransactions);
	
	public CreateAccountDetails findDetailsByCustomerAccountNo(String accNo);
	public CreateAccountDetails saveOrUpdateAccount(CreateAccountDetails createAccountDetails);
	public Transaction saveOrUpdateTransaction(Transaction transactionModel);
}
