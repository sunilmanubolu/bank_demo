package com.example.banking.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.example.banking.model.AccountType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class NewAccountRequest {
	@Nullable
	private Long registeredCustomerId;// If customer is already registered
	@Nullable
	private NewCustomerDetails customerDetails;// If customer is not registered
	@NotNull
	private AccountType accountType;
	@Min(1)
	private BigDecimal depositAmount;
}
