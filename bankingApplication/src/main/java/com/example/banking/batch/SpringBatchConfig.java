package com.example.banking.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {/*
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	@Qualifier("addInterestStep")
	protected Step addInterestStep(Tasklet addInterestTask) {
		return steps.get("addInterestStep").tasklet(addInterestTask).build();
	}

	@Bean
	@Qualifier("addInterestJob")
	public Job addInterestJob(Step addInterestStep) {
		return jobs.get("addInterestJob").start(addInterestStep).build();
	}

	@Bean
	@Qualifier("printTransactionsJob")
	public Job printTransactionsJob(Step printTransactionsStep) {
		return jobs.get("printTransactionsJob").start(printTransactionsStep).build();
	}

	@Bean
	@Qualifier("printTransactionsStep")
	public Step printTransactionsStep(Tasklet printTransactionsTask) {
		return steps.get("printTransactionsStep").tasklet(printTransactionsTask).build();
	}
*/}