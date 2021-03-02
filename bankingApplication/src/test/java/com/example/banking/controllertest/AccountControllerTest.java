package com.example.banking.controllertest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.banking.controller.AccountController;
import com.example.banking.dto.CreateAccount;
import com.example.banking.helper.BankingHelper;
import com.example.banking.repository.CreateAccountRepository;
import com.example.banking.services.BankService;
import com.example.banking.services.TransactionsServices;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {
	
	
	
	
	
		@InjectMocks
	    private AccountController accountController;

	    @Mock
	    private BankService bankService; 
	
	    private MockMvc mockMvc;
	    
	    @Before
	    public void init() {
	        this.mockMvc = MockMvcBuilders.standaloneSetup(this.accountController).build();
	    }

	/*@Test
	public void test() {
		//fail("Not yet implemented");
		assertFalse(true);
	}*/
	
	@Test
	public void createAccount() throws Exception{
		 //this.mockMvc.perform(get("/createCustomerAccount"))
		
	}
	
	//List<CreateAccount> listOfUsers= new ArrayList<>();
	//listOfUsers.add(new CreateAccount(1, "Amrutha", "Kanthimath", "amruthaKanthimath", "1000005050", "9482929767", 
	//		"abcd abcd", "Savings", "amrutha.kanthimath@gmail.com", 100000, "BVYPA5458A", "123456789A"));
	//listOfUsers.add(new CreateAccount(2, "Anitha", "Sharma", "anithaSharma", "1000005051", "9482929778", "abcd abcd", "Current", "anitha.sharma@gmail.com", 100000, "BVYPA5459B", "1234567abA"));
	//listOfUsers.add(new CreateAccount(3, "Anusha", "Hiremath", "aushaHiremath", "1000005052", "9482929789", "abcd abcd", "Savings", "ausha.hiremath@gmail.com", 100000, "BVYPA5456B", "1234567bvA"));
	//listOfUsers.add(new CreateAccount(4, "Avinash", "Sharma", "avinashSharma", "1000005053", "9482929790", "abcd abcd", "Current", "avinash.sharma@gmail.com", 100000, "BVYPA5457B", "1234567mnA"));
	//listOfUsers.add(new CreateAccount(5, "Bhavana", "Prabhu", "bhavanaPrabhu", "1000005054", "9482929780", "abcd abcd", "Savings", "bhavana.prabhu@gmail.com", 100000, "BVYPA5454B", "1234567uyA"));
	
	@Test
	public void getAccountDetailsTest() throws Exception {/*
		List<CreateAccount> listOfUsers= new ArrayList<>();
		listOfUsers.add(new CreateAccount(1, "Amrutha", "Kanthimath", "amruthaKanthimath", "1000005050", "9482929767", 
				"abcd abcd", "Savings", "amrutha.kanthimath@gmail.com", 100000, "BVYPA5458A", "123456789A"));
		Mockito.when(accountController.getListOfUsers()).thenReturn(listOfUsers);
		this.mockMvc.perform(get("/getAcc"))
		.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
     //   .andExpect("$", hasS)
        .andExpect(jsonPath("$.[0]", is(1)))
        .andExpect(jsonPath("$.[0]", is("Amrutha")))
        .andExpect(jsonPath("$.[0]", is("Kanthimath")))
        .andExpect(jsonPath("$.[0]", is("amruthaKanthimath")))
        .andExpect(jsonPath("$.[0]", is("1000005050")))
        .andExpect(jsonPath("$.[0]", is("9482929767")))
        .andExpect(jsonPath("$.[0]", is("abcd abcd")))
        .andExpect(jsonPath("$.[0]", is("Savings")))
        .andExpect(jsonPath("$.[0]", is("amrutha.kanthimath@gmail.com")))
        .andExpect(jsonPath("$.[0]", is(100000)))
        .andExpect(jsonPath("$.[0]", is("BVYPA5458A")))
        .andExpect(jsonPath("$.[0]", is("123456789A")));
		
		Mockito.verify(accountController,times(1)).getListOfUsers();
		verifyNoMoreInteractions(accountController);
       
	*/}

}
