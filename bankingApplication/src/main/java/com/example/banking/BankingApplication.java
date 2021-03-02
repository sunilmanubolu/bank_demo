package com.example.banking;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.banking.model.Account;
import com.example.banking.model.AccountStatus;
import com.example.banking.model.AccountType;
import com.example.banking.model.Address;
import com.example.banking.model.Customer;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.AddressRepository;
import com.example.banking.repository.CustomerRepository;



@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BankingApplication implements CommandLineRunner{

	private static final Logger LOGGER = LoggerFactory.getLogger(BankingApplication.class);
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Bean
	public AuditorAware<String> auditorAware() {
		return () -> Optional.of("Test");
	}
	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		// Account1
		Address address1 = new Address("test address line1", "test address line2", "test city", "test district",
				"test state", "898989");
		addressRepository.save(address1);
		Customer customer1 = new Customer("TESTPAN", "TESTAADHAR", "User", "One", LocalDate.now(), address1);
		customerRepository.save(customer1);
		Account account1 = new Account(AccountType.SAVING, customer1, 25000.0, AccountStatus.ACTIVE);
		accountRepository.save(account1);

		// Account2
		Address address2 = new Address("test address line1", "test address line2", "test city", "test district",
				"test state", "898989");
		addressRepository.save(address2);
		Customer customer2 = new Customer("TESTPAN", "TESTAADHAR", "User", "Two", LocalDate.now(), address2);
		customerRepository.save(customer2);
		Account account2 = new Account(AccountType.SAVING, customer2, 50000.0, AccountStatus.ACTIVE);
		accountRepository.save(account2);

		// Account3
		Address address3 = new Address("test address line1", "test address line2", "test city", "test district",
				"test state", "898989");
		addressRepository.save(address3);
		Customer customer3 = new Customer("TESTPAN", "TESTAADHAR", "User", "Three", LocalDate.now(), address3);
		customerRepository.save(customer3);
		Account account3 = new Account(AccountType.CURRENT, customer3, 75000.0, AccountStatus.ACTIVE);
		accountRepository.save(account3);
	}
	

}
