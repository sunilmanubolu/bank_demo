package com.example.banking.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "accounts")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account extends Auditable{
	@Id
	@GeneratedValue(generator = "accountNumberGenerator")
	@SequenceGenerator(sequenceName = "accountNumberSequence", initialValue = 1000000, name = "accountNumberGenerator")
	private Long accountNumber;
	private AccountType accountType;
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	private Double balance;
	private AccountStatus status;
	private LocalDateTime interestLastAddedOn;

	public Account(AccountType accountType, Customer customer, Double balance, AccountStatus status) {
		super();
		this.accountType = accountType;
		this.customer = customer;
		this.balance = balance;
		this.status = status;
	}

}
