package com.example.banking.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.banking.dto.TransactionDTO;
import com.example.banking.model.Transaction;
import com.example.banking.serviceImpl.TransactionsServicesImpl;

public class TransactionsServicesTest {

	@Autowired
	@Mock
	private TransactionsServices transactionService;
	
	@InjectMocks
	private TransactionsServicesImpl transactionImpl;
	
	private MockMvc mockMvc;
		
	
	private String accNo="1000005050";
	private Integer noOfTransactions=3;
	@Test
	public void findAllTransactionWithAccNoAndDaysTest() {
		
		List<TransactionDTO> tranModel= new ArrayList<TransactionDTO>();
		tranModel.add(new TransactionDTO(1, "1000005050", null, "1000005051", "HDFC001", "100000", "Anitha Sharma", "IMPS"));
		tranModel.add(new TransactionDTO(2, "1000005050", null,"1000005053", "HDFC001", "200000", "Anusha hiremath", "IMPS"));
		tranModel.add(new TransactionDTO(3, "1000005050", null,"1000005052", "HDFC001", "400000", "Anisha Sheik", "IMPS"));
		//tranModel.add(new TransactionDTO(3, "1000005052", "1000005050", "HDFC001", "400000", "Anisha Sheik", "IMPS"));
		
		List<TransactionDTO> transList=this.transactionImpl.findAllTransactionWithAccNoAndDays(accNo, noOfTransactions);
		
		assertEquals(tranModel, transList);
	}
	
}
