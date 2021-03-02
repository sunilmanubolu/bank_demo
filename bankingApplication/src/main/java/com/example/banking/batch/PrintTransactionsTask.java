package com.example.banking.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.banking.config.ApplicationConfiguration;
import com.example.banking.model.Transaction;
import com.example.banking.services.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * Tasklet for adding interest to all the accounts
 * 
 * @author poonsahu
 *
 */
@Slf4j
@Component
public class PrintTransactionsTask  {/*implements Tasklet
	@Autowired
	private ApplicationConfiguration appConfig;
	@Autowired
	private TransactionService transactionService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Sort sort = Sort.by("transactionDate").descending().and(Sort.by("transactionTime").descending());// Sort By //
																											// order
		Pageable pageRequest = PageRequest.of(0, appConfig.getChunkSize(), sort);
		Page<Transaction> transactions = transactionService.getAllTransactions(pageRequest);

		while (!transactions.isEmpty()) {
			pageRequest = pageRequest.next();
			transactions.forEach(transaction -> System.out.println(transaction));
			transactions = transactionService.getAllTransactions(pageRequest);
		}
		return RepeatStatus.FINISHED;
	}
*/}