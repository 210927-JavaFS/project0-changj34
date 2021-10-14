package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.services.AccountService;
import com.revature.services.ApplicationService;
import com.revature.services.CustomerService;

public class BankTests {

	public static Account account;
	public static Logger log = LoggerFactory.getLogger(BankTests.class);
	public static double result;
	public static int testCustomerID;
	public static int permanentTestAccountID;
	public static Customer customer;
	public static Application application;
	public CustomerService customerService = new CustomerService();
	public AccountService accountService = new AccountService();
	public ApplicationService applicationService = new ApplicationService();
	
	@BeforeEach
	public void setValues() {
		customer = new Customer("Test", "Test", "1112223333", "email@email.com", "test", "test", 3);
		account = new Account("Test", 222, java.time.LocalDate.now().toString(), 3);
		application = new Application("Test", 222, 3);
		testCustomerID = 3;
		permanentTestAccountID = 5;
	}
	
	@Test
	public void testLogin() {
		log.info("In testLogin");
		Customer temp = customerService.verifyLogin(customer.getLogin(), customer.getPassword());
		assertEquals(temp.getFirstName(), customer.getFirstName());
		assertEquals(temp.getLastName(), customer.getLastName());
	}
	
	@Test
	public void testFindAllCustomers() {
		log.info("In testFindAllCustomers");
		assertTrue(customerService.findAllCustomers().size() >= 1);
	}
	
	@Test
	public void testDenyApplication() {
		log.info("In testDenyApplication");
		applicationService.newApplication(application);
		List<Application> temp = applicationService.findAll();
		assertTrue(applicationService.deleteApplication(temp.get(temp.size() - 1).getId()));
	}
	
	@Test
	public void testApproveApplication() {
		log.info("In testApproveApplication");
		applicationService.newApplication(application);
		List<Application> temp = applicationService.findAll();
		assertTrue(accountService.newAccount(new Account(application.getAccountName(), application.getStartingBalance(), java.time.LocalDate.now().toString(), application.getCustomerID())));
		assertTrue(applicationService.deleteApplication(temp.get(temp.size() - 1).getId()));
		log.info("Testing deleting accounts on newly created account");
		List<Account> temp2 = accountService.findAllAccounts();
		assertTrue(accountService.deleteAccount(temp2.get(temp2.size()-1).getId()));
	}
	
	@Test
	public void testFindAllAccounts() {
		log.info("In testFindAllAccounts");
		assertTrue(accountService.findAllAccounts().size() >= 1);
	}
	
	@Test
	public void testGetAccount() {
		log.info("In testGetAccount");
		Account temp = accountService.getAccount(testCustomerID, account.getName());
		assertEquals(temp.getName(), account.getName());
	}
	
	@Test
	public void testFindAccountByAccountID() {
		log.info("In testFindAccountByAccountID");
		assertEquals(accountService.findAccountByAccountID(permanentTestAccountID).getId(), permanentTestAccountID);
	}
	
	@Test
	public void testDeposit() {
		log.info("In testDeposit");
		Account temp = accountService.findAccountByAccountID(permanentTestAccountID);
		temp.setBalance(temp.getBalance() + 50);
		assertTrue(accountService.updateAccount(temp));
	}
	
	@Test
	public void testWithdraw() {
		log.info("In testWithdraw");
		Account temp = accountService.findAccountByAccountID(permanentTestAccountID);
		temp.setBalance(temp.getBalance() - 50);
		assertTrue(accountService.updateAccount(temp));
	}
	
	@Test
	public void testFindAccountByID() {
		log.info("In testFindAccountByID");
		assertTrue(accountService.findAccountByAccountID(permanentTestAccountID) != null);
	}
	
	@Test
	public void testFindAccounts() {
		log.info("In testFindAccounts");
		assertTrue(accountService.findAccountsByID(testCustomerID).size() >= 1);
	}
	
	
	
}
