package com.example.banking.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.banking.dto.CreateAccount;
import com.example.banking.dto.TransactionDTO;
import com.example.banking.model.AddressDetails;
import com.example.banking.model.CreateAccountDetails;
import com.example.banking.model.Transaction;

@Component
public class BankingHelper {
	
	public CreateAccountDetails convertToCreateAccEntity(CreateAccount createAccount) {
		CreateAccountDetails details= new CreateAccountDetails();
		AddressDetails address= new AddressDetails();
		details.setId(createAccount.getId());
		/*details.setcFirstName(createAccount.getcFirstName());
		details.setcLastName(createAccount.getcLastName());
		details.setAccNumber(createAccount.getAccNumber());
		details.setAccountType(createAccount.getAccountType());
		details.setcEmailId(createAccount.getcEmailId());
		details.setcUserName(createAccount.getcUserName());
		details.setcMobileNumber(createAccount.getcMobileNumber());
		details.setcAddress(createAccount.getcAddress());
		details.setBalance(createAccount.getBalance());*/
		/*if(details.getcAddress()!=null) {
			address.setAddress1(createAccount.getcAddress().getAddress1());
			address.setAddress2(createAccount.getcAddress().getAddress2());
			address.setCity(createAccount.getcAddress().getCity());
			address.setCountry(createAccount.getcAddress().getCountry());
			address.setState(createAccount.getcAddress().getState());
			address.setZip(createAccount.getcAddress().getZip());
			details.setcAddress(address);
		}*/
		return details;
		
		
	}
	
	public Transaction convertToTransactionModel(com.example.banking.dto.TransactionDTO transactionDto) {
		Transaction transaction= new Transaction();
		if(transactionDto!=null && !transactionDto.equals("")) {
			transaction.setFromAccNo(transactionDto.getFromAccNo());
			transaction.setToAccNo(transactionDto.getToAccNo());
			transaction.setAmount(transactionDto.getAmount());
			transaction.setIfscCode(transactionDto.getIfscCode());
			transaction.setName(transactionDto.getName());
			transaction.setTransactionType(transactionDto.getTransactionType());
		}
		return transaction;
	}
	
	
	public List<com.example.banking.dto.TransactionDTO> convertToTransactionDTO(List<Transaction> transactionmodelList) {
		List<TransactionDTO> transactionDTO=new ArrayList<>();
		//TransactionDTO transaction= new TransactionDTO();
		if(transactionmodelList!=null && !transactionmodelList.equals("")) {
			
			for(Transaction transactionmodel:transactionmodelList) {
				TransactionDTO transaction= new TransactionDTO();
					transaction.setFromAccNo(transactionmodel.getFromAccNo());
					transaction.setToAccNo(transactionmodel.getToAccNo());
					transaction.setAmount(transactionmodel.getAmount());
					transaction.setIfscCode(transactionmodel.getIfscCode());
					transaction.setName(transactionmodel.getName());
					transaction.setTransactionType(transactionmodel.getTransactionType());
					transaction.setFromAccType(transactionmodel.getTransactionType());
					transaction.settId(transactionmodel.gettId());
			transactionDTO.add(transaction);
		}
		}
		return transactionDTO;
	}

}
