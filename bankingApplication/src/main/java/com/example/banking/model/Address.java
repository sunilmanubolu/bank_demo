package com.example.banking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "addresses")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address extends Auditable {
	@Id
	@GeneratedValue
	private Long addressId;

	private String line1;

	private String line2;

	private String city;

	private String district;

	private String state;

	private String pincode;

	public Address(String line1, String line2, String city, String district, String state, String pincode) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
	}

}