package com.revature.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class MenuController {

	private static Scanner scan = new Scanner(System.in);
	public static Logger log = LoggerFactory.getLogger(MenuController.class);
	private static CustomerService customerService = new CustomerService();
	private static CustomerController customerController = new CustomerController();
	private static Customer customer;
	private static AccountController accountController;
	
	public void welcomeMenu() {
		log.info("Starting the application");
		System.out.println("Welcome to the banking application!");
		System.out.println("What would you like to do? \n"
				+"1) Login \n"
				+"2) Create new Customer Profile! \n"
				+"3) Exit");
		String response = scan.nextLine();
		
		while (!response.equals("3")) {
			switch (response) {
				case "1":
					System.out.println("Enter your Login:");
					String login = scan.nextLine();
					System.out.println("Enter your Password:");
					String password = scan.nextLine();
					if (customerService.verifyLogin(login, password) == null) {
						System.out.println("No User Found.");
					} else {
						customer = customerService.verifyLogin(login, password);
						accountController = new AccountController(customer);
						accountController.accountMenu();
					}
					System.out.println("Welcome to the banking application!");
					System.out.println("What would you like to do? \n"
							+"1) Login \n"
							+"2) Create new account! \n"
							+"3) Exit");
					response = scan.nextLine();
					break;
				case "2":
					customerMenu();
					System.out.println("What would you like to do? \n"
							+"1) Login \n"
							+"2) Create new account! \n"
							+"3) Exit");
					response = scan.nextLine();
					break;
			}
		}
		log.info("Ending the application");
	}

	private void customerMenu() {
		System.out.println("Welcome to the Customer Registration Menu!");	
		customerController.addCustomer();
		log.info("Creating new customer");
		System.out.println("Returning to Main Menu.");
	}
	
}
