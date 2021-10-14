package com.revature.services;

import java.util.List;

import com.revature.daos.ApplicationDAO;
import com.revature.daos.ApplicationDAOImpl;
import com.revature.models.Application;

public class ApplicationService {
	
	private ApplicationDAO applicationDAO = new ApplicationDAOImpl();

	public boolean newApplication(Application application) {
		return applicationDAO.addApplication(application);
	}
	
	public List<Application> findAll() {
		return applicationDAO.findAll();
	}
	
	public Application findApplication(int id) {
		return applicationDAO.findApplication(id);
	}
	
	public boolean deleteApplication(int id) {
		return applicationDAO.deleteApplication(id);
	}
}
