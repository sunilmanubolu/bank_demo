package com.example.banking.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
@ToString
public class FundTransferRequest {
	@NotEmpty
	private String fromAccount;
	@Min(1)
	private BigDecimal amount;
	@NotEmpty
	private String toAccount;
	@NotEmpty
	private String ifsc;
	@NotEmpty
	private String accountHolderName;
}
