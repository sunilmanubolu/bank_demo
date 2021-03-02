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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.banking.controller.TransactionController;
import com.example.banking.dto.TransactionDTO;
import com.example.banking.helper.BankingHelper;
import com.example.banking.repository.CreateAccountRepository;
import com.example.banking.repository.TransactionsRepository;
import com.example.banking.services.TransactionsServices;

public class TransactionControllerTest {
	
	
	@Autowired
	@Mock
	public CreateAccountRepository createAccountRepository;//use user account before submitting
	
	@Autowired
	@Mock
	public TransactionsServices transactionService;
	
	@Autowired
	@Mock
	public BankingHelper bankingHelper;
	
	@Autowired
	@Mock
	public TransactionsRepository transRepo;
	
	@InjectMocks
	private TransactionController transactionController;
	
	private MockMvc mockMvc;
	
	@Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.transactionController).build();
    }

	/*@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(true, true);
	}
	*/
	@Test
	public void getAllTransactionsWithLimits() throws Exception {
		List<TransactionDTO> tranModel= new ArrayList<TransactionDTO>();
		tranModel.add(new TransactionDTO(1, "1000005050", null, "1000005051", "HDFC001", "100000", "Anitha Sharma", "IMPS"));
		
		//Mockito.when(accountController.getListOfUsers()).thenReturn(listOfUsers);
		this.mockMvc.perform(get("/allTransactions/{accNo}/{days}",1000005050,3))
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
		
		//Mockito.verify(transactionController,times(1)).getListOfUsers();
		//verifyNoMoreInteractions(transactionController);
		
	}
	
	@Test
	public void accountTransaction() {
		
	}

}
