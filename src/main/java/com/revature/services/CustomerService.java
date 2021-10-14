package com.revature.services;

import java.util.List;

import com.revature.daos.CustomerDAO;
import com.revature.daos.CustomerDAOImpl;
import com.revature.models.Customer;

public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAOImpl();
	
	public List<Customer> findAllCustomers() {
		return customerDAO.findAll();
	}
	
	public Customer verifyLogin(String login, String password) {
		Customer customer = customerDAO.findByCredentials(login, password);
		if (customer.getCustomerID() != 0) {
			return customer;
		}
		return null;
	}
	
	public boolean newCustomer (Customer customer) {
		return customerDAO.addCustomer(customer);
	}
	
}