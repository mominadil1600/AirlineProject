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
import edu.gsu.excpetions.AuthenicateException;
import edu.gsu.excpetions.LoginException;
import edu.gsu.excpetions.ReservationException;
import edu.gsu.excpetions.SQLOperationException;

public class DBQueries {
	
	/**
	 * Login method to validate user credentials
	 * @param co
	 * @throws Exception
	 */
	public static void login(Customer co) throws Exception {
			
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection
					      ("jdbc:mysql://localhost/project","root","Adimahi6");
			System.out.println("Database connected");

			// Create a statement
			PreparedStatement statement = connection.prepareStatement("SELECT *  FROM user WHERE username =? AND password =?");
				    
			statement.setString(1, co.getUserName());
			statement.setString(2, co.getPassWord());
				    
			// Execute a statement
			ResultSet resultSet = statement.executeQuery();
			
			int count = 0;

			// Iterate through the result
			while (resultSet.next()) {
				System.out.println("Userid:  " + resultSet.getInt("userid"));
				co.setCustomerID(resultSet.getInt("userid"));
				count = resultSet.getInt(1);
			}
			
			if (count == 0)
				throw new LoginException("Invalid UserName or Password!");
				     
	    
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		}
		finally {
			
			connection.close();
		}
	}    
	
	/**
	 * Add flight to database
	 * @param flight
	 * @throws Exception
	 */
	public static void addFlight(Flight flight) throws Exception {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			System.out.println("Connection is succesful.");

			// create a sql date object so we can use it in our INSERT statement
			Calendar calendar = Calendar.getInstance();
			java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());

			// the mysql insert statement for flight
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
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}
		
	}
	
	/**
	 * Delete flight from database
	 * @param flight
	 * @throws Exception
	 */
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
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}
		
	}
	
	/**
	 * Search flight based on fromCity and toCity
	 * @param co
	 * @throws Exception
	 */
	public static void searchFlights(Customer co) throws Exception {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");

			
			Flight requestFlight = co.getFlights().get(0);
			// the mysql select statement for user
			String query = "SELECT * from project.flight where fromCity=? and toCity=?";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, requestFlight.getFromCity());
			preparedStmt.setString(2, requestFlight.getToCity());
			
			ResultSet resultSet = preparedStmt.executeQuery();
			co.setFlights(new ArrayList<>()); // reset flight details from customer object
			

			List<Flight> flights = new ArrayList<>();
			co.setFlights(new ArrayList<>());
			// Iterate through the result
			while (resultSet.next()) {
				Flight flight = new Flight();

				flight.setFid(resultSet.getInt("fid"));
				flight.setName(resultSet.getString("fname"));
				flight.setNumber(resultSet.getInt("fno"));
				flight.setFromCity(resultSet.getString("fromCity"));
				flight.setToCity(resultSet.getString("toCity"));
				flight.setCapacity(resultSet.getInt("capacity"));
				flight.setStatus(resultSet.getString("status"));
				flight.setSeatsBooked(resultSet.getInt("seatsBooked"));
				flight.setDatetime(resultSet.getDate("date"));
				
				flights.add(flight);
			}
			co.getFlights().addAll(flights);
			System.out.println("Flight details are retrieved.");
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLOperationException(e.getMessage());
		} 
		finally {

			connection.close();
		}
	}

	/**
	 * Get ALL flights from database
	 * @param co
	 * @throws Exception
	 */
	public static void getAllFlights(Customer co) throws Exception {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			System.out.println("Connection is succesful.");

			// the mysql select statement for user
			String query = "SELECT * from project.flight";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			ResultSet resultSet = preparedStmt.executeQuery();
			co.setFlights(new ArrayList<>());
			List<Flight> flights = new ArrayList<>();
			// Iterate through the result 
			while (resultSet.next()) {
				Flight flight = new Flight();
				flight.setFid(resultSet.getInt("fid"));
				flight.setName(resultSet.getString("fname"));
				flight.setNumber(resultSet.getInt("fno"));
				flight.setFromCity(resultSet.getString("fromCity"));
				flight.setToCity(resultSet.getString("toCity"));
				flight.setCapacity(resultSet.getInt("capacity"));
				flight.setStatus(resultSet.getString("status"));
				flight.setSeatsBooked(resultSet.getInt("seatsBooked"));
				flight.setDatetime(resultSet.getDate("date"));
				flights.add(flight);
			}
			co.getFlights().addAll(flights);
			System.out.println("Flight details are retrieved.");
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}
	}
	
	/**
	 * Registration for a new user
	 * @param registration
	 * @param userType
	 * @throws Exception
	 */
	public static void registration(Registration registration, String userType) throws Exception {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");

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
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}
	}

	/**
	 * Book ticket for a customer
	 * @param customerID
	 * @param flight
	 * @throws Exception
	 */
	public static void bookTicket(int customerID, Flight flight) throws Exception {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			
			// checking the customer  has already have the booking with same flight
			if(checkCustomerReservation(customerID, flight)) {
			throw new ReservationException("Customer is already have booking with same flight, Please choose different flight.");
			}

			// checking flight seat availability
			if(!checkFlightSeatAvailability(flight)) {
			throw new ReservationException("Flight is full!! Please choose different flight.");
			}
			

			// create a sql date object so we can use it in our INSERT statement
			String query = " insert into project.reservation (userid, fid)" + " values (?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, customerID);
			preparedStmt.setInt(2, flight.getFid());

			// Execute a statement
			preparedStmt.executeUpdate();
			
			
			// increase the seat booked for the flight 
			query = " select * from project.flight where fid = ?";

			// create the mysql select preparedstatement
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, flight.getFid());

			// Execute a statement
			ResultSet resultSet = preparedStmt.executeQuery();
			int seatsBooked = 0;
			while (resultSet.next()) {
				seatsBooked = resultSet.getInt("seatsBooked");
			}
			System.out.println("seats booked before update :: "+seatsBooked);
			
			// create a sql date object so we can use it in our INSERT statement
			query = " update project.flight set seatsBooked = ? where fid=?";

			// create the mysql insert preparedstatement
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, (seatsBooked+1));
			preparedStmt.setInt(2, flight.getFid());

			// Execute a statement
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}

	}
	
	/**
	 * Recover password with security question, answer and username
	 * @param co
	 * @throws Exception
	 */
	public static void recoverPassword(Customer co) throws Exception {

		Connection connection = null;

		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");

		// create the mysql insert preparedstatement
		PreparedStatement statement = connection.prepareStatement(" select * from project.user where username=?");
		statement.setString(1, co.getRegistration().getUsername()); // user table primary key

		// Execute a statement
		ResultSet resultSet = statement.executeQuery();
		String password = null;
		int userid = 0; 
		while (resultSet.next()) {
		password = resultSet.getString("password");
		userid = resultSet.getInt("userid");
		}

		if(userid == 0) {
		throw new AuthenicateException(" User is not present");
		}

		statement = connection
		.prepareStatement("select * from project.registration where answer=? and securityQ=?  and userid=?");

		statement.setString(1, co.getRegistration().getAnswer());
		statement.setString(2, co.getRegistration().getSecurityQ());
		statement.setInt(3, userid);

		// Execute a statement
		resultSet = statement.executeQuery();

		boolean recordPresent = false;
		while (resultSet.next()) {
		recordPresent = true;
		}

		if(!recordPresent) {
		throw new AuthenicateException("security answer is not correct, please try again!!");
		}

		co.getRegistration().setPassword(password);

		System.out.println("Password recovered succesfully .");
		} catch (SQLException e) {
		System.out.println(e);
		throw new SQLOperationException(e.getMessage());
		} finally {

		connection.close();
		}

	}
	
	/**
	 * Checking flight is full or some seats available
	 * @param flight
	 * @return
	 * @throws Exception
	 */
	private static boolean checkFlightSeatAvailability(Flight flight) throws Exception {
		Connection connection = null;
		boolean isSeatsExist = true;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			System.out.println("Connection is succesful.");

			// the mysql select statement for user
			String query = "SELECT * from project.flight where fid=?";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, flight.getFid());
			ResultSet resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("capacity") == resultSet.getInt("seatsBooked")) {
					System.out.println("Seats FULL");
					isSeatsExist = false;
				}
			}
			System.out.println("Flight details are retrieved.");
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}
		return isSeatsExist;
	}

	/**
	 * Checking customer reservation
	 * @param customerID
	 * @param flight
	 * @return
	 * @throws Exception
	 */
	private static boolean checkCustomerReservation(int customerID, Flight flight) throws Exception {
		boolean isExist = false;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			String query = " select * from project.reservation where fid = ? and userid=?";

			// create the mysql select preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, flight.getFid());
			preparedStmt.setInt(2, customerID);

			// Execute a statement
			ResultSet resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				isExist = true;
			}
			System.out.println("Customer has already reservation : " + isExist);
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}

		return isExist;

	}
	
	/**
	 * Get flights for a customer
	 * @param co
	 * @throws Exception
	 */
	public static void getFlights(Customer co) throws Exception {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "Adimahi6");
			System.out.println("Connection is succesful.");
			
			String query = " select * from project.reservation where userid=?";

			// create the mysql select preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, co.getCustomerID());

			// Execute a statement
			ResultSet resultSet = preparedStmt.executeQuery();
			List<Integer> fids = new ArrayList<>();
			int fid;
			while (resultSet.next()) {
				fid = resultSet.getInt("fid");
				fids.add(fid);
			}
			
			System.out.println("flight list: "+fids);
			List<Flight> flights = new ArrayList<>();
			co.setFlights(new ArrayList<>());
			for(int i=0; i< fids.size();i++) {
				// the mysql select statement for flight
				query = "SELECT * from project.flight where fid =?";			

				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, fids.get(i));
				resultSet = preparedStmt.executeQuery();
				
				// Iterate through the result
				while (resultSet.next()) {
					Flight flight = new Flight();
					flight.setFid(resultSet.getInt("fid"));
					flight.setName(resultSet.getString("fname"));
					flight.setNumber(resultSet.getInt("fno"));
					flight.setFromCity(resultSet.getString("fromCity"));
					flight.setToCity(resultSet.getString("toCity"));
					flight.setCapacity(resultSet.getInt("capacity"));
					flight.setStatus(resultSet.getString("status"));
					flight.setSeatsBooked(resultSet.getInt("seatsBooked"));
					flight.setDatetime(resultSet.getDate("date"));
					flights.add(flight);
				}
			}

			co.getFlights().addAll(flights);
			System.out.println("Flight details are retrieved.");
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLOperationException(e.getMessage());
		} finally {

			connection.close();
		}
	}
	

}



