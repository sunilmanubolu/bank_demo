package com.example.banking.dto;



public class TransactionDTO {
	
	public long tId;
	public String fromAccNo;
	public String fromAccType;
	public String toAccNo;
	public String ifscCode;
	public String amount;
	public String name;
	public String transactionType;
	
	
	
	
	public TransactionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionDTO(long tId, String fromAccNo, String fromAccType, String toAccNo, String ifscCode,
			String amount, String name, String transactionType) {
		super();
		this.tId = tId;
		this.fromAccNo = fromAccNo;
		this.fromAccType = fromAccType;
		this.toAccNo = toAccNo;
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
	public String getFromAccType() {
		return fromAccType;
	}
	public void setFromAccType(String fromAccType) {
		this.fromAccType = fromAccType;
	}
	public String getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(String fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public String getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
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
