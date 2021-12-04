package edu.gsu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import edu.gsu.excpetions.LoginException;

public class DBQueries {
	
	public static void login(Customer co) throws Exception {
			
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection
					      ("jdbc:mysql://localhost/project","root","Adimahi6");
					       // ("jdbc:mysql://104.196.113.68/test","root","password");
					
			System.out.println("Database connected");

			// Create a statement
			PreparedStatement statement = connection.prepareStatement(Queries.LOGIN);
				    
			statement.setString(1, co.getUserName());
			statement.setString(2, co.getPassword());
				    
			// Execute a statement
			ResultSet resultSet = statement.executeQuery();
			
			int count = 0;

			// Iterate through the result and print the student names
			while (resultSet.next()) {
				System.out.println("Number of Users:" + resultSet.getInt(1));
				count = resultSet.getInt(1);
			}
			
			if (count == 0)
				throw new LoginException("Invalid UserName or Password!");
				     
	    
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			System.out.println(e);
			throw e;
		}
		finally {
			
			connection.close();
		}
	}    
	
	
	public static void getFlights(Customer co) throws Exception {
		
		
		
		Flight f1 = new Flight();
		f1.setAirlineName("Delta");
		
		
		
		Flight f2 = new Flight();
		f2.setAirlineName("Spirit");
		
		
		Flight f3 = new Flight();
		f3.setAirlineName("American Airlines");
		
		
		co.getFlights().add(f1);
		co.getFlights().add(f2);
		co.getFlights().add(f3);
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Customer c0 = new Customer();
		login(c0);
		
		
	}
	/*
	
	REGISTRATION QUERY
	
	public static void register(Customer reg) throws Exception{
		
	
		Connection con = null;
		
		try {
			con = DriverManager.getConnection
					      ("jdbc:mysql://localhost/project","root","Adimahi6");
			
			
			System.out.println("Database connected");
			
			
			PreparedStatement statement = con.prepareStatement(Queries.REGISTRATION);
		
			statement.setString(1, reg.getUserName());
			statement.setString(2, reg.getPassword());
			
	}
	
	*/
	
	
	
	
	
}



