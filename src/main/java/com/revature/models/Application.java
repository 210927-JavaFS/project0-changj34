package com.revature.models;

public class Application {
	
	private int id;
	private String accountName;
	private double startingBalance;
	private int customerID;
	
	public Application(String accountName, double startingBalance, int customerID) {
		super();
		this.accountName = accountName;
		this.startingBalance = startingBalance;
		this.customerID = customerID;
	}

	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(double startingBalance) {
		this.startingBalance = startingBalance;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + customerID;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(startingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (customerID != other.customerID)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(startingBalance) != Double.doubleToLongBits(other.startingBalance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", accountName=" + accountName + ", startingBalance=" + startingBalance
				+ ", customerID=" + customerID + "]";
	}
	
	

}
