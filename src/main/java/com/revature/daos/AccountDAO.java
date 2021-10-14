package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> findAll();
	public List<Account> findAccounts(int id);
	public Account findAccount(int id, String name);
	public boolean addAccount(Account account);
	public boolean updateAccount(Account account);
	public Account findAccount(int id);
	public boolean deleteAccount(int id);
	
	
}
