package com.example.banking.batch;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.banking.config.ApplicationConfiguration;
import com.example.banking.model.Account;
import com.example.banking.model.TransactionType;
import com.example.banking.services.AccountService;
import com.example.banking.services.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * Tasklet for printing all the transactions
 * 
 * @author poonsahu
 *
 */
@Slf4j
@Component
public class AddInterestTask  {/*implements Tasklet
	@Autowired
	private ApplicationConfiguration appConfig;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception { // order
		StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
		JobParameters jobParameters = stepExecution.getJobParameters();
		String currentBatchStartTimeString = jobParameters.getString("currentBatchStartTime");
		LocalDateTime currentBatchStartTime = LocalDateTime.parse(currentBatchStartTimeString);
		Pageable pageRequest = PageRequest.of(0, appConfig.getChunkSize());
		Page<Account> accounts = accountService.getAllAccounts(pageRequest);

		while (!accounts.isEmpty()) {
			pageRequest = pageRequest.next();
			accounts.forEach(account -> addInterest(account, currentBatchStartTime));
			accounts = accountService.getAllAccounts(pageRequest);
		}
		return RepeatStatus.FINISHED;
	}

	private void addInterest(Account account, LocalDateTime currentBatchRun) {
		LocalDateTime lastBatchRun = account.getInterestLastAddedOn() != null ? account.getInterestLastAddedOn()
				: account.getCreatedAt();// If InterestLastAddedOn is not present then use createdAt

		long time = ChronoUnit.MINUTES.between(lastBatchRun, currentBatchRun);// In minutes (t)
		if (time == 0) {
			return;
		}
		double principle = account.getBalance(); // Principle amount (p)
		float rate = appConfig.getInterestPercentPerMinute() / 100;// Per minute (r)
		int n = 1;// number of compounding periods per unit t (n)
		double newBalance = principle * Math.pow((1 + (rate / n)), (double) n * time);
		double interest = newBalance - account.getBalance();
		log.info(account.getAccountNumber() + " : " + time + "minutes : balance : " + account.getBalance()
				+ " : new balance : " + newBalance);

		transactionService.createNewTransaction("Interest credited", currentBatchRun.toLocalDate(),
				currentBatchRun.toLocalTime(), TransactionType.CREDIT, interest, newBalance, account);
		account.setInterestLastAddedOn(currentBatchRun);
		account.setBalance(newBalance);
		accountService.addInterestToAccount(account,interest);
	}
*/}