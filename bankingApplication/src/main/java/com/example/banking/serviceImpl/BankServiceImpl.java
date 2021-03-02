package com.example.banking.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.model.Customer;
import com.example.banking.exception.AccountAlreadyExistsException;
import com.example.banking.exception.BadRequestException;
import com.example.banking.exception.ExistingAccountException;
import com.example.banking.exception.ResourceNotFoundException;
import com.example.banking.dto.NewCustomerDetails;
import com.example.banking.model.Address;
import com.example.banking.model.AccountStatus;
import com.example.banking.config.ApplicationConfiguration;
import com.example.banking.dto.CreateAccount;
import com.example.banking.dto.NewAccountRequest;
import com.example.banking.helper.BankingEnum;
import com.example.banking.helper.BankingHelper;
import com.example.banking.model.Account;
import com.example.banking.model.AccountType;
import com.example.banking.model.CreateAccountDetails;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.AddressRepository;
import com.example.banking.repository.BankRepository;
import com.example.banking.repository.CreateAccountRepository;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.services.BankService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BankServiceImpl implements BankService{
	
	private static final String CUSTOMER_NOT_FOUND = "No Customer found with customer id ";
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
	
	@Autowired
	public BankingHelper bankingHelper;
	@Autowired
	public CreateAccountRepository createAccountRepository;
	@Override
	public String addCustomer(CreateAccount createAcc) {
		CreateAccountDetails createAccountDetails= new CreateAccountDetails();
		if(createAcc!=null && !createAcc.equals("")) {
			CreateAccountDetails accDetails=bankingHelper.convertToCreateAccEntity(createAcc);
			if(accDetails!=null && accDetails.getAccountType().equalsIgnoreCase("Current")) {
				accDetails.setCreateDateTime(new Date());
				System.out.println("Account creation of Current account");
				createAccountDetails=findByEmailIdAndPhoneNo(accDetails.getcEmailId(), accDetails.getcMobileNumber());
						//createAccountRepository.findByEmailIdAndPhoneNo(accDetails.getcEmailId(), accDetails.getcMobileNumber());
				if(createAccountDetails.getAccountType().equalsIgnoreCase("Current")) {
					System.out.println("Customer account already exist for Current Account!");
				}else {
					//createAccountRepository.save(accDetails);
					System.err.println("Account Created successfully!");
				}
				return "Account Created successfully!";
			}else if(accDetails!=null && accDetails.getAccountType().equalsIgnoreCase("Savings")) {
				System.out.println("Account creation of Savings account");
				accDetails.setCreateDateTime(new Date());
				//createAccountRepository.save(accDetails);
				
				createAccountDetails=findByEmailIdAndPhoneNo(accDetails.getcEmailId(), accDetails.getcMobileNumber());;
						//createAccountRepository.findByEmailIdAndPhoneNo(accDetails.getcEmailId(), accDetails.getcMobileNumber());
				if(createAccountDetails.getAccountType().equalsIgnoreCase("Savings")) {
					System.err.println("Customer account already exist for Savings Account!");
				}else {
					//createAccountRepository.save(accDetails);
					System.err.println("Account Created successfully!");
				}
				return "Account Created successfully!";
				
			}
		}
		return "Account Is not created Due to IMproper Data!";
		
	}
	@Override
	public CreateAccount checkForAccExistance(String emailId, String mobileNo) {
		
		//CreateAccount createAccount = new CreateAccount();
		// TODO Auto-generated method stub
		return null;
	}
	
	public CreateAccountDetails findByEmailIdAndPhoneNo(String emailId,String mobilNo) {
		CreateAccountDetails createAccountDetails= new CreateAccountDetails();
		createAccountDetails.setAccountType("Current");
		return createAccountDetails;
	}
	@Override
	public long createNewAccount(CreateAccount accountDetails) {
		// TODO Auto-generated method stub
		Customer customer = null;
		
				// If the information provided is incomplete
				if (accountDetails.getCustomerId() == null && accountDetails.getCustomerDetails() == null) {
					log.info(INCOMPLETE_INFORMATION);
					throw new BadRequestException(INCOMPLETE_INFORMATION);
				}
				
				if (accountDetails.getCustomerId() != null) {// If an registered customer previously
					customer = getCustomerDetailsById(accountDetails.getCustomerId());
					log.info("Already registered customer " + customer.getCustmerId());
					List<Account> accounts = accountRepository.findByCustomer(customer);

					// Get all registered account type
					/*Set<AccountType> registeredAccountTypes = accounts.stream().map(account -> account.getAccountType())
							.collect(Collectors.toSet());*/
					List<AccountType> registeredAccountType=null;
					for(Account account:accounts) {
						AccountType acco=null;
						acco=account.getAccountType();
						registeredAccountType.add(acco);
					}
					Set<AccountType> allAccountTypes = new HashSet<>(Arrays.asList(AccountType.values()));
					Boolean result=registeredAccountType.containsAll(allAccountTypes);
					
					if(result) {
						log.info("Customer already has 2 or more accounts");
						throw new ExistingAccountException(MULTIPLE_ACCOUNTS_MESSAGE);
					}
					
					/*Set<AccountType> allAccountTypes = new HashSet<>(Arrays.asList(AccountType.values()));

					// Unregistered account types = all account types - registered account types
					allAccountTypes.removeAll(registeredAccountTypes);
					if (allAccountTypes.isEmpty()) {
						log.info("Customer already has 2 or more accounts");
						throw new ExistingAccountException(MULTIPLE_ACCOUNTS_MESSAGE);
					}*/

					// if this account type is already created for the user
					if(registeredAccountType.contains(accountDetails.getAccountType())) {
					//if (!allAccountTypes.contains(accountDetails.getAccountType())) {

						throw new ExistingAccountException(
								accountDetails.getAccountType().toString() + " account has already been created.");
					}

				} else {
					//validateMinimumBalance(accountDetails.getAccountType(), accountDetails.getDepositAmount());
					customer = addNewCustomer(accountDetails.getCustomerDetails());
				}
				
				
			    validateMinimumBalance(accountDetails.getAccountType(), accountDetails.getDepositAmount());
				Account account = new Account(accountDetails.getAccountType(), customer,
						accountDetails.getDepositAmount().doubleValue(), AccountStatus.ACTIVE);
				log.info("Saving Account with details= {} in the dB.", account.toString());
				Account savedAccount = accountRepository.save(account);// save account in db
				return savedAccount.getAccountNumber();
	}
	
	@Override
	public void validateMinimumBalance(AccountType accountType, double depositAmount) {
		if ((accountType == AccountType.CURRENT && depositAmount < 10000.0)
				|| (accountType == AccountType.SAVING && depositAmount < 5000.0)) {
			throw new BadRequestException(String.format(MINIMUM_DEPOSIT_MESSAGE,
					(accountType == AccountType.SAVING) ?
							  5000.0
							: 10000.0,
					accountType.toString()));
		}
	}
	
	@SuppressWarnings("null")
	@Override
	public Customer getCustomerDetailsById(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer==null && customer.equals("")) {
			log.info(CUSTOMER_NOT_FOUND+customerId);
			throw new ResourceNotFoundException(CUSTOMER_NOT_FOUND+customerId);
		}
		return customer.get();
	}
	
	
	private Customer addNewCustomer(NewCustomerDetails customerDetails) {
		Customer customer=new Customer();
		if(customerDetails!=null) {
		validatePanAadhar(customerDetails.getAadhar(), customerDetails.getPan());// validate pan and aadhar
		Address address = new Address(customerDetails.getLine1(), customerDetails.getLine2(), customerDetails.getCity(),
				customerDetails.getDistrict(), customerDetails.getState(), customerDetails.getPincode());
		log.info("Address details persisting in the dB."+ address.toString());
		address = addressRepository.save(address);// save address in db

		 customer = new Customer(customerDetails.getPan(), customerDetails.getAadhar(),
				customerDetails.getFirstName(), customerDetails.getLastName(), customerDetails.getBirthDate(), address);
		log.info("Saving Customer with details= {} in the dB.", customer.toString());
		}
		return customerRepository.save(customer);// save customer in db
	}
	
	
	private void validatePanAadhar(String pan, String aadhar) {
		Optional<Customer> customer = customerRepository.findByAadharOrPan(aadhar, pan);
		if (!customer.equals("") && customer!=null) {//if (!customer.isEmpty())
			log.info("Account already exists with Aadhar"+aadhar+" or PAN "+ pan);
			throw new ExistingAccountException(aadhar, pan);
		}

	}

}
