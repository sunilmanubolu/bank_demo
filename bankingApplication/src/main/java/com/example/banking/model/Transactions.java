package com.example.banking.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "transaction_details")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	private String description;
	private LocalDate transactionDate;
	private LocalTime transactionTime;
	private TransactionType transactionType;
	private double amount;
	private double closingBalance;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Account account;

	public Transactions(String description, LocalDate transactionDate, LocalTime transactionTime,
			TransactionType transactionType, double amount, double closingBalance, Account account) {
		super();
		this.description = description;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.transactionType = transactionType;
		this.amount = amount;
		this.closingBalance = closingBalance;
		this.account = account;
	}

}
