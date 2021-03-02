package com.example.banking.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.banking.model.Transaction;

@Repository
public interface TransactionsRepository extends CrudRepository<Transaction, Long>{

	/*@Query("select t from Transaction t where t.fromAccNo=?1")
	public List<Transaction> findByAccountNumber(String accountNo);*/
}
