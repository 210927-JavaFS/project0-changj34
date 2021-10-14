package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;

public class EmployeeService {
	
	private CustomerService customerService = new CustomerService();
	private AccountService accountService = new AccountService();
	private ApplicationService applicationService = new ApplicationService();

	public void displayAllApplications() {
		List<Application> list = applicationService.findAll();
		if (list.isEmpty()) {
			System.out.println("There are no applications.");
		} else {
			System.out.println("These are the applications:");
			for (Application application:list) {
				System.out.println(application);
			}
		}
	}
	
	public void displayAllCustomers() {
		List<Customer> list = customerService.findAllCustomers();
		if (list.isEmpty()) {
			System.out.println("There are no customers.");
		} else {
			System.out.println("These are your customers:");
			for (Customer customer:list) {
				System.out.println(customer);
			}
		}
	}
	
	public void displayAllAccounts() {
		List<Account> list = accountService.findAllAccounts();
		if (list.isEmpty()) {
			System.out.println("There are no accounts");
		} else {
			System.out.println("These are all active accounts:");
			for (Account account:list) {
				System.out.println(account);
			}
		}
	}
}
