package com.example.banking.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails {
	
	@NotEmpty
	private String pancardNumber;
	@NotEmpty
	private String aadharCard;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotNull
	private LocalDate birthDate;
	@NotEmpty
	private String addressline1;
	@NotNull
	private String addressline2;
	@NotEmpty
	private String city;
	@NotEmpty
	private String district;
	@NotEmpty
	private String state;
	@NotEmpty
	private String pincode;

}
