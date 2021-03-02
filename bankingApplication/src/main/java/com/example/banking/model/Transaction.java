package com.example.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long tId;
	@Column(name="toAccNo")
	public String toAccNo;
	@Column(name="fromAccNo")
	public String fromAccNo;
	@Column(name="tIFSCCode")
	public String ifscCode;
	@Column(name="tAmount")
	public String amount;
	@Column(name="tName")
	public String name;
	@Column
	public String transactionType;

	
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(long tId, String toAccNo, String fromAccNo, String ifscCode, String amount, String name,
			String transactionType) {
		super();
		this.tId = tId;
		this.toAccNo = toAccNo;
		this.fromAccNo = fromAccNo;
		this.ifscCode = ifscCode;
		this.amount = amount;
		this.name = name;
		this.transactionType = transactionType;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}
	public String getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(String fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public long gettId() {
		return tId;
	}
	public void settId(long tId) {
		this.tId = tId;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
