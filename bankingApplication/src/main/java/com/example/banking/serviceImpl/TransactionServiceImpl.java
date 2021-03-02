package com.example.banking.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.banking.model.Account;
import com.example.banking.model.AccountStatus;
import com.example.banking.model.AccountType;
import com.example.banking.model.Customer;
import com.example.banking.model.Transaction;
import com.example.banking.model.TransactionType;
import com.example.banking.services.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TransactionServiceImpl {/*//implements TransactionService 
	private static final String SAME_ACCOUNT_MESSAGE = "Cannot transfer funds to the same account";
	private static final String NO_ACCOUNT_MESSAGE = "No Account found for Account number: %s";
	private static final String LOCKED_ACCOUNT_MESSAGE = "Account number: %s is locked";
	private static final String INSUFFICIENT_FUND_MESSAGE = "Insufficient funds in Account number: %s";

	@Autowired
	private ApplicationConfiguration config;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionsRepository transactionRepository;

	*//**
	 * Transfers the fund from one account to another
	 * 
	 * @param fundTransferRequest contains fund transfer details such as from
	 *                            account, to account, amount
	 * 
	 * @return id of the transaction
	 *//*
	@Override
	public Long transferFund(FundTransferRequest fundTransferRequest) {
		if (fundTransferRequest.getFromAccount().equals(fundTransferRequest.getToAccount())) {
			log.info(SAME_ACCOUNT_MESSAGE + ": {}", fundTransferRequest.getFromAccount());
			throw new InvalidTransactionException(SAME_ACCOUNT_MESSAGE);// throw if tried to transfer to same account
		}

		Account fromAccount = getAccountDetails(fundTransferRequest.getFromAccount());// fetch sender account details
		Account toAccount = getAccountDetails(fundTransferRequest.getToAccount());// fetch receiver account details
		Customer receiverDetails = toAccount.getCustomer();
		String customerFullName = receiverDetails.getFirstName() + " " + receiverDetails.getLastName();
		if (!customerFullName.equalsIgnoreCase(fundTransferRequest.getAccountHolderName())) {
			throw new InvalidTransactionException("Account details do not match");
		}
		if (fromAccount.getStatus() == AccountStatus.LOCKED) {
			String message = String.format(LOCKED_ACCOUNT_MESSAGE, fromAccount.getAccountNumber());
			log.info(message);
			throw new InvalidTransactionException(message);// throw if account is locked

		}
		if (fromAccount.getBalance() < fundTransferRequest.getAmount().doubleValue()) {
			String message = String.format(INSUFFICIENT_FUND_MESSAGE, fromAccount.getAccountNumber());
			log.info(message);
			throw new InvalidTransactionException(message);// throw if account doesn't have sufficient funds
		}

		return processTransfer(fromAccount, toAccount, fundTransferRequest.getAmount().doubleValue());
	}

	*//**
	 * Deposits the amount to given accountNumber
	 * 
	 * @param depositRequest
	 * @return transaction id
	 *//*
	@Override
	public long deposit(DepositWithdrawRequest depositRequest) {
		Account account = getAccountDetails(depositRequest.getAccountNumber());// fetch account details
		Customer customerDetails = account.getCustomer();
		String customerFullName = customerDetails.getFirstName() + " " + customerDetails.getLastName();
		log.info(customerFullName);
		log.info(depositRequest.getAccountHolderName());
		if (!customerFullName.equalsIgnoreCase(depositRequest.getAccountHolderName())) {
			throw new InvalidTransactionException("Account details do not match");
		}
		double amountToDeposit = depositRequest.getAmount().doubleValue();
		LocalDate transactionDate = LocalDate.now();// Date of Transaction
		LocalTime transactionTime = LocalTime.now();// Time of Transaction
		double newBalance = account.getBalance() + amountToDeposit;
		account.setBalance(newBalance);// Add amount to account
		account.setStatus(checkAccountStatus(account.getBalance(), account.getAccountType()));
		accountRepository.save(account);
		Transaction transactionDetails = createNewTransaction("Deposit " + account.getAccountNumber(), transactionDate,
				transactionTime, TransactionType.CREDIT, amountToDeposit, newBalance, account);// Add transaction
																								// details to the
																								// account
		return transactionDetails.getTransactionId();
	}

	*//**
	 * Withdraws the amount from given accountNumber
	 * 
	 * @param withdraw request
	 * @return transaction id
	 *//*
	@Override
	public long withdraw(DepositWithdrawRequest withdrawRequest) {
		Account account = getAccountDetails(withdrawRequest.getAccountNumber());// fetch account details
		Customer customerDetails = account.getCustomer();
		String customerFullName = customerDetails.getFirstName() + " " + customerDetails.getLastName();
		if (!customerFullName.equalsIgnoreCase(withdrawRequest.getAccountHolderName())) {
			throw new InvalidTransactionException("Account details do not match");
		}
		double amountToWithdraw = withdrawRequest.getAmount().doubleValue();
		if (account.getBalance() < amountToWithdraw) {
			String message = String.format(INSUFFICIENT_FUND_MESSAGE, account.getAccountNumber());
			log.info(message);
			throw new InvalidTransactionException(message);
		}
		LocalDate transactionDate = LocalDate.now();// Date of Transaction
		LocalTime transactionTime = LocalTime.now();// Time of Transaction
		double newBalance = account.getBalance() - amountToWithdraw;
		account.setBalance(newBalance);// Deduct amount from account
		account.setStatus(checkAccountStatus(account.getBalance(), account.getAccountType()));
		accountRepository.save(account);
		Transaction transactionDetails = createNewTransaction("Withdraw " + account.getAccountNumber(), transactionDate,
				transactionTime, TransactionType.DEBIT, amountToWithdraw, newBalance, account);// Add transaction
																								// details to the
																								// account
		return transactionDetails.getTransactionId();
	}

	*//**
	 * Gets all the transactions for given account for given no. of days
	 * 
	 * @param days          no. of days including current date
	 * @param accountNumber
	 * @return list of transaction
	 *//*
	@Override
	public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber, int days) {
		Account account = getAccountDetails(accountNumber);// fetch account details
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusDays(days - 1);
		log.info(endDate.toString());
		log.info(startDate.toString());
		return transactionRepository.findByAccountAndTransactionDateBetween(account, startDate, endDate);
	}

	*//**
	 * Makes the transaction and by debiting amount from sender and adding it to
	 * receiver's account
	 * 
	 * @param fromAccount
	 * @param toAccount
	 * @param amountToTransfer
	 * @return transaction id
	 *//*
	private Long processTransfer(Account fromAccount, Account toAccount, double amountToTransfer) {
		LocalDate transactionDate = LocalDate.now();// Date of Transaction
		LocalTime transactionTime = LocalTime.now();// Time of Transaction

		double newBalanceForFromAccount = fromAccount.getBalance() - amountToTransfer;
		fromAccount.setBalance(newBalanceForFromAccount);// Deduct amount from sender's account
		fromAccount.setStatus(checkAccountStatus(fromAccount.getBalance(), fromAccount.getAccountType()));

		double newBalanceForToAccount = toAccount.getBalance() + amountToTransfer;
		toAccount.setBalance(newBalanceForToAccount);// Add amount to receiver's account
		toAccount.setStatus(checkAccountStatus(toAccount.getBalance(), toAccount.getAccountType()));
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		Transaction transactionDetails = createNewTransaction("Transfer to " + toAccount.getAccountNumber(),
				transactionDate, transactionTime, TransactionType.DEBIT, amountToTransfer, newBalanceForFromAccount,
				fromAccount);// Add transaction details to sender's account
		createNewTransaction("Received from " + fromAccount.getAccountNumber(), transactionDate, transactionTime,
				TransactionType.CREDIT, amountToTransfer, newBalanceForFromAccount, toAccount);// Add transaction
																								// details to receiver's
																								// account
		return transactionDetails.getTransactionId();
	}

	*//**
	 * Returns latest account status
	 * 
	 * @param balance
	 * @param accountType
	 * @return Latest account status by matching with the rule
	 *//*
	@Override
	public AccountStatus checkAccountStatus(double balance, AccountType accountType) {
		double minimumBalance = 0;
		if (accountType == AccountType.CURRENT)
			minimumBalance = config.getMinimumBalanceCurrent();// set minimum balance for current account
		else if (accountType == AccountType.SAVING)
			minimumBalance = config.getMinimumBalanceSaving();// set minimum balance for saving account
		// if current balance is more than or equal to minimum balance then make it as
		// active else locked
		return balance > minimumBalance ? AccountStatus.ACTIVE : AccountStatus.LOCKED;
	}

	*//**
	 * Fetches Account details from db for given account number
	 * 
	 * @param accountNumber
	 * @return Account
	 * @throws ResourceNotFoundException if no account is found
	 *//*
	@Override
	public Account getAccountDetails(String accountNumber) {
		Optional<Account> account = accountRepository.findById(Long.valueOf(accountNumber));
		if (account.isEmpty()) {
			String message = String.format(NO_ACCOUNT_MESSAGE, accountNumber);
			log.info(message);
			throw new ResourceNotFoundException(message);
		}
		return account.get();
	}

	*//**
	 * Returns all the transactions
	 * 
	 * @param pageable
	 * @return list of transactions
	 *//*
	@Override
	public Page<Transaction> getAllTransactions(Pageable pageable) {
		return transactionRepository.findAll(pageable);
	}

	*//**
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
	 *//*
	@Override
	public Transaction createNewTransaction(String description, LocalDate transactionDate, LocalTime transactionTime,
			TransactionType transactionType, double transactionAmount, double closingBalance, Account account) {
		Transaction transaction = new Transaction(description, transactionDate, transactionTime, transactionType,
				transactionAmount, closingBalance, account);
		return transactionRepository.save(transaction);
	}

*/}
