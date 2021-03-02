package com.example.banking.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.banking.dto.DepositWithdrawRequest;
import com.example.banking.dto.FundTransferRequest;
import com.example.banking.model.Account;
import com.example.banking.model.AccountStatus;
import com.example.banking.model.AccountType;
import com.example.banking.model.Transaction;
import com.example.banking.model.TransactionType;
import com.example.banking.exception.ResourceNotFoundException;

/**
 * Service for Transaction related features
 * 
 * @author poonsahu
 *
 */

public interface TransactionService {
	/**
	 * Transfers the fund from one account to another
	 * 
	 * @param fundTransferRequest contains fund transfer details such as from
	 *                            account, to account, amount
	 * 
	 * @return id of the transaction
	 */
	Long transferFund(FundTransferRequest fundTransferRequest);

	/**
	 * Gets all the transactions for given account for given no. of days
	 * 
	 * @param days          no. of days including current date
	 * @param accountNumber
	 * @return list of transaction
	 */
	public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber, int days);

	/**
	 * Returns all the transactions
	 * 
	 * @param pageable
	 * @return list of transactions
	 */
	public Page<Transaction> getAllTransactions(Pageable pageable);

	/**
	 * Creates a new trasaction in the db
	 * 
	 * @param description
	 * @param transactionDate
	 * @param transactionTime
	 * @param transactionType
	 * @param transactionAmount
	 * @param closingBalance
	 * @param account
	 * @return Transaction
	 */
	public Transaction createNewTransaction(String description, LocalDate transactionDate, LocalTime transactionTime,
			TransactionType transactionType, double transactionAmount, double closingBalance, Account account);

	/**
	 * Fetches Account details from db for given account number
	 * 
	 * @param accountNumber
	 * @return Account
	 * @throws ResourceNotFoundException if no account is found
	 */
	Account getAccountDetails(String accountNumber);

	/**
	 * Returns latest account status
	 * 
	 * @param balance
	 * @param accountType
	 * @return Latest account status by matching with the rule
	 */
	AccountStatus checkAccountStatus(double balance, AccountType accountType);

	/**
	 * Deposits the amount to given accountNumber
	 * 
	 * @param depositRequest
	 * @return transaction id
	 */
	long deposit(@Valid DepositWithdrawRequest depositRequest);

	/**
	 * Withdraws the amount from given accountNumber
	 * 
	 * @param withdraw request
	 * @return transaction id
	 */
	long withdraw(DepositWithdrawRequest withdrawRequest);
}
