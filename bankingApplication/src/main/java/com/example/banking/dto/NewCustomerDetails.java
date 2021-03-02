package com.example.banking.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class NewCustomerDetails {
	@NotEmpty
	private String pan;
	@NotEmpty
	private String aadhar;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotNull
	private LocalDate birthDate;
	@NotEmpty
	private String line1;
	@NotNull
	private String line2;
	@NotEmpty
	private String city;
	@NotEmpty
	private String district;
	@NotEmpty
	private String state;
	@NotEmpty
	private String pincode;
}
