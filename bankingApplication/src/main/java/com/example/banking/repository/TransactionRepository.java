package com.example.banking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.banking.model.Account;
import com.example.banking.model.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
	List<Transactions> findByAccountAndTransactionDateBetween(Account account,LocalDate startDate,LocalDate endDate);
}
