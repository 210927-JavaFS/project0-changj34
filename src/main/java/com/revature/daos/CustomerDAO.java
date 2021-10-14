package com.revature.daos;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDAO {

	public List<Customer> findAll();
	public Customer findByCredentials(String login, String password);
	public boolean updateCustomer(Customer customer);
	public boolean addCustomer(Customer customer);
	
}
