package com.example.banking.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.example.banking.model.AccountType;
import com.example.banking.model.AddressDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccount {
	
	
	@Nullable
	private Long customerId;// If customer is already registered
	@Nullable
	private NewCustomerDetails customerDetails;// If customer is not registered
	@NotNull
	public AccountType accountType;
	@Min(1)
	public Long depositAmount;
	
	@Nullable
	public Integer id;
	@Nullable
	public String cFirstName;
	@Nullable
	public String cLastName;
	@Nullable
	public String cUserName;
	@Nullable
	public String accNumber;
	@Nullable
	public String cMobileNumber;
	@Nullable
	public String cAddress;
	@Nullable
	public String cEmailId;
	@Nullable
	public String panCardNo;
	@Nullable
	public String aadharCardNo;
	
	
	
	
	

}
