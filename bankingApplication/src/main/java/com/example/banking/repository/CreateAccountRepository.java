package com.example.banking.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.banking.model.CreateAccountDetails;

@Repository
public interface CreateAccountRepository extends CrudRepository<CreateAccountDetails, Integer>{
	
	/*@Query("select c from CREATE_CUST_ACCOUNT c where c.CUST_EMAILID=?1 and c.CUST_MOBILENUMBER=?2")
	public CreateAccountDetails findByEmailIdAndPhoneNo(String emailId,String phoneNo);
	
	@Query("select c from CREATE_CUST_ACCOUNT c where c.CUST_ACCNUMBER=?1")
	public CreateAccountDetails findByCustomerAccountNo(String accNo);*/

}
