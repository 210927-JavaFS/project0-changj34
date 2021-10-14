package com.revature.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.services.AccountService;
import com.revature.services.ApplicationService;

public class ApplicationController {

	private static Scanner scan = new Scanner(System.in);
	public static Logger log = LoggerFactory.getLogger(ApplicationController.class);
	private Application application;
	private ApplicationService applicationService = new ApplicationService();
	private AccountService accountService = new AccountService();
	
	public void applicationMenu() {
		log.info("In application menu");
		System.out.println("Would you like to approve or deny any application? \n"
				+"1) Approve \n"
				+"2) Deny \n"
				+"3) Exit");
		String response = scan.nextLine();
		int id = 0;
		while (!response.equals("3")) {
			switch (response) {
				case "1":
					log.info("Approving application");
					System.out.println("What is the ID of the application to approve?");
					boolean loop = true;
					while (loop) {
						try {
							response = scan.nextLine();
							id = Integer.parseInt(response);
							if (id <= 0) {
								System.out.println("Invalid input. Try again.");
								continue;
							}
							loop = false;
							break;
						} catch (NumberFormatException e) {
							System.out.println("Invalid input. Try again.");
						}
					}
					application = applicationService.findApplication(id);
					Account account = new Account(application.getAccountName(), application.getStartingBalance(), java.time.LocalDate.now().toString(), application.getCustomerID());
					if (accountService.newAccount(account) && applicationService.deleteApplication(id)) {
						System.out.println("Application approved and account created!");
					} else {
						System.out.println("Something went wrong. Please try again.");
					}
					System.out.println("Would you like to approve or deny any application? \n"
							+"1) Approve \n"
							+"2) Deny \n"
							+"3) Exit");
					response = scan.nextLine();
					break;
				case "2":
					log.info("Denying application");
					System.out.println("What is the ID of the application to deny?");
					boolean loop2 = true;
					while (loop2) {
						try {
							response = scan.nextLine();
							id = Integer.parseInt(response);
							if (id <= 0) {
								System.out.println("Invalid input. Try again.");
								continue;
							}
							loop2 = false;
							break;
						} catch (NumberFormatException e) {
							System.out.println("Invalid input. Try again.");
						}
					}
					if (applicationService.deleteApplication(id)) {
						System.out.println("Application denied.");
					} else {
						System.out.println("Something went wrong. Please try again.");
					}
					System.out.println("Would you like to approve or deny any application? \n"
							+"1) Approve \n"
							+"2) Deny \n"
							+"3) Exit");
					response = scan.nextLine();
					break;
			}
		}
	}
	
}
