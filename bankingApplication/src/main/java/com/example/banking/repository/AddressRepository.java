package com.example.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
