package com.example.banking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.dto.TransactionDTO;
import com.example.banking.helper.BankingHelper;
import com.example.banking.model.CreateAccountDetails;
import com.example.banking.model.Transaction;
import com.example.banking.repository.CreateAccountRepository;
import com.example.banking.repository.TransactionsRepository;
import com.example.banking.services.TransactionsServices;

@Service
public class TransactionsServicesImpl implements TransactionsServices{

	@Autowired
	TransactionsRepository transactionRepository;
	@Autowired
	public BankingHelper bankingHelper;
	
	@Autowired
	public CreateAccountRepository createAccountRepository;
	
	@Override
	public void transaferAmount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TransactionDTO> findAllTransactionWithAccNoAndDays(String accNo,Integer noOfTransactions) {
		// TODO Auto-generated method stub
		List<TransactionDTO> transactionListDTO=new ArrayList<>();
		List<Transaction> tranList= new ArrayList<>();
		List<Transaction> noOfTranList= new ArrayList<>();
		try {
		//Integer noOfTrans = Integer.getInteger(noOfTransactions);
		 tranList=findByAccountNumberTransactions();
				 //transactionRepository.findByAccountNumber(accNo);
		 if(tranList!=null && !tranList.equals("")) {
			 for(int i=0; i<=noOfTransactions-1;i++) {
			
				 noOfTranList.add(tranList.get(i));
			}
			 
			 transactionListDTO= bankingHelper.convertToTransactionDTO(noOfTranList);
		}else {
			System.out.println("There is no Transaction on this account number!");
		}
		 
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return transactionListDTO;
	}
	
	public List<Transaction> findByAccountNumberTransactions() {
		List<Transaction> tranModel= new ArrayList<Transaction>();
		
		tranModel.add(new Transaction(1, "1000005051", "1000005050", "HDFC001", "100000", "Anitha Sharma", "IMPS"));
		tranModel.add(new Transaction(2, "1000005053", "1000005050", "HDFC001", "200000", "Anusha hiremath", "IMPS"));
		tranModel.add(new Transaction(3, "1000005052", "1000005050", "HDFC001", "400000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(4, "1000005052", "1000005050", "HDFC001", "500000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(5, "1000005052", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(6, "1000005054", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(7, "1000005051", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(8, "1000005053", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(9, "1000005052", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(10, "1000005056", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(11, "1000005055", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(12, "1000005054", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(13, "1000005052", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(13, "1000005051", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		tranModel.add(new Transaction(14, "1000005053", "1000005050", "HDFC001", "300000", "Anisha Sheik", "IMPS"));
		return tranModel;
		
	}

	@Override
	public CreateAccountDetails findDetailsByCustomerAccountNo(String accNo) {
		// TODO Auto-generated method stub
		CreateAccountDetails createAccountDetails= new CreateAccountDetails();
		createAccountDetails.setBalance(100);
		createAccountDetails.setAccountType("Savings");
		createAccountDetails.setStatus("Locked");
				//createAccountRepository.findByCustomerAccountNo(accNo);
		return createAccountDetails;
	}

	@Override
	public CreateAccountDetails saveOrUpdateAccount(CreateAccountDetails createAccountDetails) {
		// TODO Auto-generated method stub
		CreateAccountDetails createAccountDetail;
		createAccountDetail=createAccountRepository.save(createAccountDetails);
		return createAccountDetail;
	}

	@Override
	public Transaction saveOrUpdateTransaction(Transaction transactionModel) {
		// TODO Auto-generated method stub
		Transaction transaction=transactionRepository.save(transactionModel);
		return transaction;
	}

}
