package com.example.banking.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long custmerId;

	private String pan;

	private String aadhar;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;
	@OneToOne

	private Address address;

	public Customer(String pan, String aadhar, String firstName, String lastName, LocalDate birthDate,
			Address address) {
		super();
		this.pan = pan;
		this.aadhar = aadhar;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.address = address;
	}

}