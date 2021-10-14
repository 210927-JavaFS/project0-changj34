package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;

public class AccountService {
	private AccountDAO accountDAO = new AccountDAOImpl();
	
	public List<Account> findAllAccounts() {
		return accountDAO.findAll();
	}
	
	public List<Account> findAccountsByID(int id) {
		return accountDAO.findAccounts(id);
	}
	
	public Account findAccountByAccountID(int id) {
		Account acc = accountDAO.findAccount(id);
		if (acc.getId() == 0) {
			return null;
		}
		return accountDAO.findAccount(id);
	}
	
	public Account getAccount(int customerID, String name) {
		return accountDAO.findAccount(customerID, name);
	}

	public boolean newAccount (Account account) {
		return accountDAO.addAccount(account);
	}
	
	public boolean updateAccount (Account account) {
		return accountDAO.updateAccount(account);
	}
	
	public boolean deleteAccount (int id) {
		return accountDAO.deleteAccount(id);
	}
}
