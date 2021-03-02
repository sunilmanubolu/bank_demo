package com.example.banking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="CREATE_CUST_ACCOUNT")
public class CreateAccountDetails {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CUST_ID")
	public Integer id;
	@Column(name="CUST_FIRSTNAME")
	public String cFirstName;
	@Column(name="CUST_LASTNAME")
	public String cLastName;
	@Column(name="CUST_USERNAME")
	public String cUserName;
	@Column(name="CUST_ACCNUMBER")
	public String accNumber;
	@Column(name="CUST_MOBILENUMBER")
	public String cMobileNumber;
	@Column(name="CUST_ADDRESS")
	public String cAddress;
	@Column(name="CUST_ACCTYPE")
	public String accountType;
	@Column(name="CUST_EMAILID")
	public String cEmailId;
	@Column(name="CUST_BALANCE")
	public long balance;
	@Column
	public Date createDateTime;
	
	@Column
	public String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getcFirstName() {
		return cFirstName;
	}

	public void setcFirstName(String cFirstName) {
		this.cFirstName = cFirstName;
	}

	public String getcLastName() {
		return cLastName;
	}

	public void setcLastName(String cLastName) {
		this.cLastName = cLastName;
	}

	public String getcUserName() {
		return cUserName;
	}

	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getcMobileNumber() {
		return cMobileNumber;
	}

	public void setcMobileNumber(String cMobileNumber) {
		this.cMobileNumber = cMobileNumber;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getcEmailId() {
		return cEmailId;
	}

	public void setcEmailId(String cEmailId) {
		this.cEmailId = cEmailId;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
