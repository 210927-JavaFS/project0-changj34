package com.revature.daos;

import java.util.List;

import com.revature.models.Application;

public interface ApplicationDAO {
	
	public List<Application> findAll();
	public Application findApplication(int id);
	public boolean addApplication(Application application);
	public boolean deleteApplication(int id);
}
