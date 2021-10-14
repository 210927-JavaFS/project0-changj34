package com.revature.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.services.AccountService;
import com.revature.services.ApplicationService;
import com.revature.services.EmployeeService;
import com.revature.utils.ValidateUtil;

public class AccountController {

	private static Scanner scan = new Scanner(System.in);
	public static Logger log = LoggerFactory.getLogger(AccountController.class);
	private Customer customer;
	private Account account;
	private AccountService accountService = new AccountService();
	private EmployeeService employeeService = new EmployeeService();
	private ApplicationService applicationService = new ApplicationService();
	private ApplicationController applicationController = new ApplicationController();
	private ValidateUtil validateUtil = new ValidateUtil();
	private Iterator<Account> itr = null;
	private int power;
	
	public AccountController(Customer customer) {
		super();
		this.customer = customer;
		this.power = customer.getPower();
	}
	
	public void accountMenu() {
		log.info("In account options");
		System.out.println("Welcome " + customer.getFirstName() + "!");
		System.out.println("What would you like to do today? \n"
				+"1) Access an account \n"
				+"2) Create an account \n"
				+"3) Main Menu");
		if (power >= 2) {
			System.out.println("0) Employee Menu");
		}
		String response = scan.nextLine();
		while (!response.equals("3")) {
			switch (response) {
			case "1":
				itr = accountService.findAccountsByID(customer.getCustomerID()).iterator();
				if (itr.hasNext()) {
					System.out.println("Which account would you like to access?");
					log.info("Accessing accounts");
					while(itr.hasNext()) {
						System.out.println(itr.next().getName());
					}
					response = scan.nextLine();
					account = accountService.getAccount(customer.getCustomerID(), response);
					while (account.getId() == 0) {
						System.out.println("Account not found. Try again.");
						log.warn("No account found");
						response = scan.nextLine();
						account = accountService.getAccount(customer.getCustomerID(), response);
					}
					accountOptions();
				} else {
					System.out.println("Sorry! There are no active accounts.");
				}
				System.out.println("Returning to Account Services.");
				System.out.println("What would you like to do today? \n"
						+"1) Access an account \n"
						+"2) Create an account \n"
						+"3) Main Menu");
				response = scan.nextLine();
				break;
			case "2":
				
				addAccount();
				System.out.println("What would you like to do today? \n"
						+"1) Access an account \n"
						+"2) Create an account \n"
						+"3) Main Menu");
				response = scan.nextLine();
				break;
			}
			if (response.equals("0") && power >= 2) {
				employeeMenu();
				System.out.println("What would you like to do today? \n"
						+"1) Access an account \n"
						+"2) Create an account \n"
						+"3) Main Menu \n"
						+"0) Employee Menu");
				response = scan.nextLine();
				break;
			}
		}
		System.out.println("Returning to Main Menu.");
	}
	
	private void employeeMenu() {
		log.info("In employee menu");
		System.out.println("What would you like to do? \n"
				+"1) View all customers \n"
				+"2) View all accounts \n"
				+"3) View applications \n"
				+"4) Return to account services");
		String response = scan.nextLine();
		while (!response.equals("4")) {
			switch (response) {
			case "1":
				log.info("Displaying all customers");
				employeeService.displayAllCustomers();
				System.out.println("Return to Employee menu. \n"
						+"What would you like to do? \n"
						+"1) View all customers \n"
						+"2) View all accounts \n"
						+"3) View applications \n"
						+"4) Return to account services");
				response = scan.nextLine();
				break;
			case "2":
				log.info("Displaying all accounts");
				employeeService.displayAllAccounts();
				if (power == 3) {
					log.info("In admin menu");
					adminMenu();
				}
				System.out.println("Return to Employee menu. \n"
						+"What would you like to do? \n"
						+"1) View all customers \n"
						+"2) View all accounts \n"
						+"3) View applications \n"
						+"4) Return to account services");
				response = scan.nextLine();
				break;
			case "3":
				log.info("Displaying all applications");
				employeeService.displayAllApplications();
				applicationController.applicationMenu();
				System.out.println("Return to Employee menu. \n"
						+"What would you like to do? \n"
						+"1) View all customers \n"
						+"2) View all accounts \n"
						+"3) View applications \n"
						+"4) Return to account services");
				response = scan.nextLine();
				break;
			}
		}
		log.info("Leaving employee menu");
	}
	
	private void adminMenu() {
		System.out.println("What would you like to do to an account? \n"
				+"1) Deposit to an account \n"
				+"2) Withdraw from an account \n"
				+"3) Transfer funds between accounts \n"
				+"4) Cancel an account \n"
				+"5) Exit");
		String response = scan.nextLine();
		while (!response.equals("5")) {
			switch (response) {
				case "1":
					log.info("Depositing to an account as admin");
					System.out.println("What is the account ID to deposit to?");
					int id = validateUtil.stringToInt(scan);
					Account temp = accountService.findAccountByAccountID(id);
					if (temp == null) {
						log.warn("No account found");
						System.out.println("Could not find Account ID. Try again.");
						response = scan.nextLine();
						break;
					}
					deposit(temp);
					System.out.println("What would you like to do to an account? \n"
							+"1) Deposit to an account \n"
							+"2) Withdraw from an account \n"
							+"3) Transfer funds between accounts \n"
							+"4) Cancel an account \n"
							+"5) Exit");
					response = scan.nextLine();
					break;
				case "2":
					log.info("Withdrawing from an account as admin");
					System.out.println("What is the account ID to withdraw from?");
					int id2 = validateUtil.stringToInt(scan);
					Account temp2 = accountService.findAccountByAccountID(id2);
					if (temp2 == null) {
						log.warn("No account found");
						System.out.println("Could not find Account ID. Try again.");
						response = scan.nextLine();
						break;
					}
					withdraw(temp2);
					System.out.println("What would you like to do to an account? \n"
							+"1) Deposit to an account \n"
							+"2) Withdraw from an account \n"
							+"3) Transfer funds between accounts \n"
							+"4) Cancel an account \n"
							+"5) Exit");
					response = scan.nextLine();
					break;
				case "3":
					log.info("Transferring between accounts as admin");
					System.out.println("What is the account ID you want to transfer from?");
					int id3 = validateUtil.stringToInt(scan);
					Account temp3 = accountService.findAccountByAccountID(id3);
					if (temp3 == null) {
						log.warn("No account found");
						System.out.println("Could not find Account ID. Try again.");
						response = scan.nextLine();
						break;
					}
					System.out.println("What is the account ID you want to transfer to?");
					int id4 = validateUtil.stringToInt(scan);
					Account temp4 = accountService.findAccountByAccountID(id4);
					if (temp4 == null) {
						log.warn("No account found");
						System.out.println("Could not find Account ID. Try again.");
						response = scan.nextLine();
						break;
					}
					transferFunds(temp3, temp4);
					System.out.println("What would you like to do to an account? \n"
							+"1) Deposit to an account \n"
							+"2) Withdraw from an account \n"
							+"3) Transfer funds between accounts \n"
							+"4) Cancel an account \n"
							+"5) Exit");
					response = scan.nextLine();
					break;
				case "4":
					log.info("Deleting an account as admin");
					System.out.println("What is the account ID to cancel?");
					int id5 = validateUtil.stringToInt(scan);
					if (accountService.deleteAccount(id5)) {
						System.out.println("Account successfully cancelled.");
					} else {
						System.out.println("Something went wrong. Try again.");
					}
					System.out.println("What would you like to do to an account? \n"
							+"1) Deposit to an account \n"
							+"2) Withdraw from an account \n"
							+"3) Transfer funds between accounts \n"
							+"4) Cancel an account \n"
							+"5) Exit");
					response = scan.nextLine();
					break;
			}
		}
		log.info("Exiting admin menu");
	}

	private void accountOptions() {
		List<Account> accounts = accountService.findAccountsByID(customer.getCustomerID());
		log.info("Accessed account");
		System.out.println("Which would you like to do? \n"
				+"1) Deposit \n"
				+"2) Withdraw \n"
				+"3) Check Balance");
		if (accounts.size() >= 2) {
			System.out.println("4) Transfer Funds");
		}
		String response = scan.nextLine();
		switch (response) {
		case "1":
			deposit(account);
			break;
		case "2":
			withdraw(account);
			break;
		case "3":
			log.info("Checking balance");
			System.out.println("Your current balance is " + account.getBalance() + ".");
			break;
		}
		if (response.equals("4") && accounts.size() >= 2) {
			itr = accounts.iterator();
			System.out.println("Which account do you want to transfer funds to?");
			while(itr.hasNext()) {
				Account temp = itr.next();
				if (!temp.getName().equals(account.getName())) {
					System.out.println(temp.getName());
				}
			}
			response = scan.nextLine();
			Account accountToTransferTo = accountService.getAccount(customer.getCustomerID(), response);
			transferFunds(account, accountToTransferTo);
		}
	}
	
	private void transferFunds(Account account, Account accountToTransferTo) {
		log.info("Transferring funds between owned accounts");
		System.out.println("How much would you like to transfer?");
		String response;
		double transfer = 0;
		while (transfer == 0) {
			try {
				response = scan.nextLine();
				transfer = Double.parseDouble(response);
				if (transfer >= account.getBalance()) {
					log.warn("Insufficient funds");
					System.out.println("Insufficient funds. Try again.");
					transfer = 0;
					continue;
				} else if (transfer <= 0) {
					log.warn("Invalid input");
					System.out.println("Invalid input. Try again.");
					transfer = 0;
					continue;
				}
				break;
			} catch (NumberFormatException e) {
				log.warn("Invalid input");
				System.out.println("Invalid input. Try again.");
			}
		}
		account.setBalance(account.getBalance()-transfer);
		accountToTransferTo.setBalance(accountToTransferTo.getBalance()+transfer);
		if (accountService.updateAccount(account) && accountService.updateAccount(accountToTransferTo)) {
			log.info("Transfer complete");
			System.out.println("Transfer Complete. Your new balance is " + account.getBalance() + ".");
		} else {
			System.out.println("Something went wrong. We could not complete your withdrawal. Please try again.");
			log.warn("Transfer cancelled");
		}
		
	}
	
	private void withdraw(Account account) {
		log.info("In withdraw");
		boolean loop = true;
		do {
			System.out.println("How much would you like to withdraw?");
			double withdraw = 0;
			String response;
			while (withdraw == 0) {
				try {
					response = scan.nextLine();
					withdraw = Double.parseDouble(response);
					if (withdraw >= account.getBalance()) {
						log.warn("Insufficient funds");
						System.out.println("Insufficient funds. Try again.");
						withdraw = 0;
						continue;
					}
					break;
				} catch (NumberFormatException e) {
					log.warn("Invalid input");
					System.out.println("Invalid input. Try again.");
				}
			}
			System.out.println("Withdraw " + withdraw + "? \n"
					+"1) Yes \n"
					+"2) No");
			response = scan.nextLine();
			switch (response) {
				case "1":
					account.setBalance(account.getBalance()-withdraw);
					if (accountService.updateAccount(account)) {
						log.info("Withdrawal complete");
						System.out.println("Complete. Your new balance is " + account.getBalance() + ".");
						loop = false;
					} else {
						log.warn("Withdrawal cancelled");
						System.out.println("Something went wrong. We could not complete your withdrawal. Please try again.");
					}
					break;
				case "2":
					break;
			}
		} while (loop);
	}

	private void deposit(Account account) {
		log.info("In deposit");
		boolean loop = true;
		do {
			System.out.println("How much would you like to deposit?");
			double deposit = 0;
			String response;
			while (deposit == 0) {
				try {
					response = scan.nextLine();
					deposit = Double.parseDouble(response);
					break;
				} catch (NumberFormatException e) {
					log.warn("Invalid input");
					System.out.println("Invalid input. Try again.");
				}
			}
			System.out.println("Deposit " + deposit + "? \n"
					+"1) Yes \n"
					+"2) No");
			response = scan.nextLine();
			switch (response) {
				case "1":
					account.setBalance(account.getBalance()+deposit);
					if (accountService.updateAccount(account)) {
						log.info("Deposit complete");
						System.out.println("Complete. Your new balance is " + account.getBalance() + ".");
						loop = false;
					} else {
						log.warn("Deposit cancelled");
						System.out.println("Something went wrong. We could not complete your deposit. Please try again.");
					}
					break;
				case "2":
					break;
			}
		} while (loop);
	}
	
	private void addAccount() {
		log.info("Creating account application");
		System.out.println("What will be your account's name?");
		String name = scan.nextLine();
		System.out.println("How much money would you like to deposit?");
		double deposit = 0;
		while (deposit == 0) {
			try {
				String response = scan.nextLine();
				deposit = Double.parseDouble(response);
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again.");
				log.warn("Invalid input");
			}
		}
		Application application = new Application(name, deposit, customer.getCustomerID());
		if (applicationService.newApplication(application)) {
			log.info("Application submitted");
			System.out.println("Congratulations! Your application was successfully submitted. \n"
					+ "Please wait while an employee reviews it.");
		} else {
			System.out.println("Something went wrong. We could not register your account. Please try again.");
			log.warn("Error in application");
		}
	}
}
