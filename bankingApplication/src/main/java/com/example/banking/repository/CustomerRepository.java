package com.example.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByAadharOrPan(String aadhar, String pan);
}
