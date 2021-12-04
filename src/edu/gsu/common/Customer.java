package edu.gsu.common;

import java.util.ArrayList;

public class Customer extends User{
	
	private int customerID;
	private ArrayList<Flight> flights;
	private Registration registration;
	private String action;
	
	
	public Customer() {
		
		flights = new ArrayList<>();
	}
	
	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public Registration getRegistration() {
		return registration;
	}


	public void setRegistration(Registration registration) {
		this.registration = registration;
	}


	public ArrayList<Flight> getFlights() {
		return flights;
	}


	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

}
