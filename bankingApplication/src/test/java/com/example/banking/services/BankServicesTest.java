package com.example.banking.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.banking.dto.CreateAccount;
import com.example.banking.serviceImpl.BankServiceImpl;


public class BankServicesTest {
	@Autowired
	@Mock
	private BankService bankService;
	
	@Autowired
	@InjectMocks
	private BankServiceImpl serviceImpl;
	
	

	
	@Test
	public void addCustomerTest() {/*
		CreateAccount createAcc= new CreateAccount(01, "Amrutha", "Kanthimath",
				"amruthaKanthimath", "1000005050", "9482929767", "ABCD ABCD","Savings", 
				"amrutha.kanthimath@capgemini.com", 200000, "BVYPA5458A", "12233455677889");
		String result=this.serviceImpl.addCustomer(createAcc);
		assertEquals("Account Created successfully!", result);
	*/}
	
	
	

}
