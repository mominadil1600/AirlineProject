package edu.gsu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import edu.gsu.common.Registration;
import edu.gsu.excpetions.LoginException;

public class DBQueries {
	
	public static void login(Customer co) throws Exception {
			
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection
					      ("jdbc:mysql://localhost/project","root","Adimahi6");
			System.out.println("Database connected");

			// Create a statement
			PreparedStatement statement = connection.prepareStatement(Queries.LOGIN);
				    
			statement.setString(1, co.getUserName());
			statement.setString(2, co.getPassWord());
				    
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
	
	public static void addFlight(Flight flight) throws Exception {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			System.out.println("Connection is succesful.");

			// create a sql date object so we can use it in our INSERT statement
			Calendar calendar = Calendar.getInstance();
			java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());

			// the mysql insert statement for user
			String query = " insert into project.flight (fname, fno, fromCity, toCity, capacity, status, seatsBooked, date)" + " values (?, ?, ?,?,?,?,?,?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, flight.getName());
			preparedStmt.setInt(2, flight.getNumber());
			preparedStmt.setString(3, flight.getFromCity());
			preparedStmt.setString(4, flight.getToCity());
			preparedStmt.setInt(5, flight.getCapacity());
			preparedStmt.setString(6, flight.getStatus());
			preparedStmt.setInt(7, 0);
			preparedStmt.setDate(8,date);

			// Execute a statement
			preparedStmt.executeUpdate();
			System.out.println("Flight details are added .");
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} finally {

			connection.close();
		}
		
	}
	
	
	public static void deleteFlight(Flight flight) throws Exception {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			System.out.println("Connection is succesful.");

			// the mysql delete statement for user
			String query = " delete from project.flight where fno=?" ;

			// create the mysql delete preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, flight.getNumber());

			// Execute a statement
			preparedStmt.executeUpdate();
			System.out.println("Flight details are deleted .");
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} finally {

			connection.close();
		}
		
	}

	
	public static void getFlights(Customer co) throws Exception {

		 
		 Connection connection = null;

		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
		System.out.println("Connection is succesful.");

		// the mysql delete statement for user
		String query = "SELECT * from project.flight";

		// create the mysql delete preparedstatement
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		ResultSet resultSet = preparedStmt.executeQuery();
		co.setFlights(new ArrayList<>());
		List<Flight> flights = new ArrayList<>();
		// Iterate through the result and print the student names
		while (resultSet.next()) {
		Flight flight = new Flight();
		flight.setFid(resultSet.getInt("fid"));
		flight.setName(resultSet.getString("fname"));
		flight.setNumber(resultSet.getInt ("fno"));
		flight.setFromCity(resultSet.getString ("fromCity"));
		flight.setToCity(resultSet.getString ("toCity"));
		flight.setCapacity(resultSet.getInt ("capacity"));
		flight.setStatus(resultSet.getString ("status"));
		flight.setSeatsBooked(resultSet.getInt ("seatsBooked"));
		flight.setDatetime(resultSet.getDate ("date"));
		flights.add(flight);
		}
		co.getFlights().addAll(flights);
		System.out.println("Flight details are retrieved.");
		} catch (SQLException e) {
		System.out.println(e);
		throw e;
		} finally {

		connection.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		//Customer c0 = new Customer();
		//login(c0);
		Flight flight = new Flight("AirIndia",100,"Atlanta","Hyderabad",10,"OPEN");
		flight.setFid(2);
		addFlight(flight);
		//deleteFlight(flight);
		//bookTicket(1,flight);
		
	}

	public static void registration(Registration registration, String userType) throws Exception {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			// ("jdbc:mysql://104.196.113.68/test","root","password");

			// create a sql date object so we can use it in our INSERT statement
			Calendar calendar = Calendar.getInstance();
			java.sql.Date registrationDate = new java.sql.Date(calendar.getTime().getTime());

			// the mysql insert statement for user
			String query = " insert into project.user (usertype, username, password)" + " values (?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, userType);
			preparedStmt.setString(2, registration.getUsername());
			preparedStmt.setString(3, registration.getPassword());

			// Execute a statement
			preparedStmt.executeUpdate();

			System.out.println("User details are inserted .");

			PreparedStatement statement = connection
					.prepareStatement("select * from project.user where username=? and password=?");

			statement.setString(1, registration.getUsername());
			statement.setString(2, registration.getPassword());

			// Execute a statement
			ResultSet resultSet = statement.executeQuery();
			int userId = 0;
			while (resultSet.next()) {
				userId = resultSet.getInt("userid");
			}
			System.out.println(userId + "UserID");

			// the mysql insert statement for registration
			query = " insert into project.registration (userid, firstName, lastName, address, zip, state, email, ssn, securityQ, answer)"
					+ " values (?, ?, ?,?, ?, ?,?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, userId); // user table primary key
			preparedStmt.setString(2, registration.getFirstName());
			preparedStmt.setString(3, registration.getLastName());
			preparedStmt.setString(4, registration.getAddress());
			preparedStmt.setInt(5, registration.getZip());
			preparedStmt.setString(6, registration.getState());
			preparedStmt.setString(7, registration.getEmail());
			preparedStmt.setString(8, registration.getSsn());
			preparedStmt.setString(9, registration.getSecurityQ());
			preparedStmt.setString(10, registration.getAnswer());

			// Execute a statement
			preparedStmt.executeUpdate();

			System.out.println("Registration details are inserted .");
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} finally {

			connection.close();
		}
	}

	public static void bookTicket(int customerID, Flight flight) throws Exception {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");

			// create a sql date object so we can use it in our INSERT statement
			String query = " insert into project.reservation (userid, fid)" + " values (?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, customerID);
			preparedStmt.setInt(2, flight.getFid());

			// Execute a statement
			preparedStmt.executeUpdate();

			System.out.println("Reservation details are inserted .");
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} finally {

			connection.close();
		}

	}

}



