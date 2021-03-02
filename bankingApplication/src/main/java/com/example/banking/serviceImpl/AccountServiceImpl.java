package com.example.banking.serviceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.banking.config.ApplicationConfiguration;
import com.example.banking.dto.NewAccountRequest;
import com.example.banking.dto.NewCustomerDetails;
import com.example.banking.model.Account;
import com.example.banking.model.AccountStatus;
import com.example.banking.model.AccountType;
import com.example.banking.model.Address;
import com.example.banking.model.Customer;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.AddressRepository;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.exception.AccountAlreadyExistsException;
import com.example.banking.exception.BadRequestException;
import com.example.banking.exception.ResourceNotFoundException;
import com.example.banking.services.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	private static final String CUSTOMER_NOT_FOUND = "No Customer found with customer id : %s";
	private static final String INCOMPLETE_INFORMATION = "Incomplete information provided by user";
	private static final String MULTIPLE_ACCOUNTS_MESSAGE = "Only 2 accounts are allowed for one customer";
	private static final String MINIMUM_DEPOSIT_MESSAGE = "Minimum deposit should be %s rs. for %s account";

	@Autowired
	private ApplicationConfiguration config;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Creates new account
	 * 
	 * @param accountDetails
	 * @return account number of newly created account
	 */
	@Override
	public long createNewAccount(NewAccountRequest accountDetails) {
		Customer customer = null;
		// If provided information is incomplete
		if (accountDetails.getRegisteredCustomerId() == null && accountDetails.getCustomerDetails() == null) {
			log.info(INCOMPLETE_INFORMATION);
			throw new BadRequestException(INCOMPLETE_INFORMATION);
		}
		if (accountDetails.getRegisteredCustomerId() != null) {// If already registered customer
			customer = getCustomerDetailsById(accountDetails.getRegisteredCustomerId());
			log.info("Already registered customer " + customer.getCustmerId());
			List<Account> accounts = accountRepository.findByCustomer(customer);

			// Get all registered account type
			Set<AccountType> registeredAccountTypes = accounts.stream().map(account -> account.getAccountType())
					.collect(Collectors.toSet());
			Set<AccountType> allAccountTypes = new HashSet<>(Arrays.asList(AccountType.values()));

			// Unregistered account types = all account types - registered account types
			allAccountTypes.removeAll(registeredAccountTypes);
			if (allAccountTypes.isEmpty()) {
				log.info("Customer already has 2 or more accounts");
				throw new AccountAlreadyExistsException(MULTIPLE_ACCOUNTS_MESSAGE);
			}

			// if this account type is already created for the user
			if (!allAccountTypes.contains(accountDetails.getAccountType())) {

				throw new AccountAlreadyExistsException(
						accountDetails.getAccountType().toString() + " account is already created.");
			}

		} else {

			customer = addNewCustomer(accountDetails.getCustomerDetails());
		}

		validateMinimumBalance(accountDetails.getAccountType(), accountDetails.getDepositAmount().doubleValue());
		Account account = new Account(accountDetails.getAccountType(), customer,
				accountDetails.getDepositAmount().doubleValue(), AccountStatus.ACTIVE);
		log.info("Saving Account with details= {} in the dB.", account.toString());
		Account savedAccount = accountRepository.save(account);// save account in db
		return savedAccount.getAccountNumber();
	}

	/**
	 * Creates a new customer in the db
	 * 
	 * @param customerDetails
	 * @return Customer
	 */
	private Customer addNewCustomer(NewCustomerDetails customerDetails) {
		validatePanAadhar(customerDetails.getAadhar(), customerDetails.getPan());// validate pan and aadhar
		Address address = new Address(customerDetails.getLine1(), customerDetails.getLine2(), customerDetails.getCity(),
				customerDetails.getDistrict(), customerDetails.getState(), customerDetails.getPincode());
		log.info("Saving Address with details= {} in the dB.", address.toString());
		address = addressRepository.save(address);// save address in db

		Customer customer = new Customer(customerDetails.getPan(), customerDetails.getAadhar(),
				customerDetails.getFirstName(), customerDetails.getLastName(), customerDetails.getBirthDate(), address);
		log.info("Saving Customer with details= {} in the dB.", customer.toString());
		return customerRepository.save(customer);// save customer in db
	}

	/**
	 * Validates aadhar and pan
	 * 
	 * @param pan
	 * @param aadhar
	 * @throws AccountAlreadyExistsException if account already exists with given
	 *                                       aadhar or pan
	 */
	private void validatePanAadhar(String pan, String aadhar) {
		Optional<Customer> customer = customerRepository.findByAadharOrPan(aadhar, pan);
		/*if (!customer.isEmpty()) {
			log.info("Account already exists with Aadhar \"{}\" or PAN \"{}\"", aadhar, pan);
			throw new AccountAlreadyExistsException(aadhar, pan);
		}*/

	}

	/**
	 * Fetches customer details for given customer id
	 * 
	 * @param customerId
	 * @return Customer
	 * @throws ResourceNotFoundException if no customer found
	 */
	@Override
	public Customer getCustomerDetailsById(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		/*if (customer.isEmpty()) {
			String message = String.format(CUSTOMER_NOT_FOUND, customerId);
			log.info(message);
			throw new ResourceNotFoundException(message);
		}*/
		return customer.get();
	}

	/**
	 * Validates Minimum balance
	 * 
	 * @param accountType
	 * @param depositAmount
	 * @throws BadRequestException if minimum balance is not met
	 */
	@Override
	public void validateMinimumBalance(AccountType accountType, double depositAmount) {
		if ((accountType == AccountType.CURRENT && depositAmount < config.getMinimumBalanceCurrent())
				|| (accountType == AccountType.SAVING && depositAmount < config.getMinimumBalanceSaving())) {
			throw new BadRequestException(String.format(MINIMUM_DEPOSIT_MESSAGE,
					(accountType == AccountType.SAVING) ? config.getMinimumBalanceSaving()
							: config.getMinimumBalanceCurrent(),
					accountType.toString()));
		}
	}

	/**
	 * Returns all the accounts
	 * 
	 * @param pageable
	 * @return list of accounts
	 */
	@Override
	public Page<Account> getAllAccounts(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	/**
	 * Add Interest to the account and save
	 * 
	 * @param account
	 * @param interest
	 * @return Account
	 */
	@Override
	public Account addInterestToAccount(Account account, double interest) {
		double newBalance = account.getBalance() + interest;
		account.setBalance(newBalance);
		return accountRepository.save(account);
	}
}
