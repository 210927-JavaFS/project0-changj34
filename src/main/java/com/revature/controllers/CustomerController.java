package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class CustomerController {
	
	private CustomerService customerService = new CustomerService();
	private Scanner scan = new Scanner(System.in);
	
	
	public void addCustomer() {
		System.out.println("What is your first name?");
		String firstName = scan.nextLine();
		System.out.println("What is your last name?");
		String lastName = scan.nextLine();
		System.out.println("What is your phone number?");
		String phone = scan.nextLine();
		System.out.println("What is your email?");
		String email = scan.nextLine();
		System.out.println("What is your login?");
		String login = scan.nextLine();
		System.out.println("What is your password?");
		String password = scan.nextLine();
		
		Customer customer = new Customer(firstName, lastName, phone, email, login, password, 1);
		
		if(customerService.newCustomer(customer)) {
			System.out.println("Your new profile was created!");
		} else {
			System.out.println("Something went wrong! Please try again.");
		}
	}
}
