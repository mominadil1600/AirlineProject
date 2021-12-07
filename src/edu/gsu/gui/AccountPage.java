package edu.gsu.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import edu.gsu.common.Action;
import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;






public class AccountPage extends Application {

	private Customer customer;
	Button addFlight = new Button("Add Flight");
	Button back = new Button("Back");
	
	
	Button bookTickets = new Button("Book Ticket");
	Button submitFlight = new Button("Submit");
	Button searchFlight = new Button("Search Flight");
	Button logout = new Button("Logout");
	Button allFlights = new Button("All Flights");
	Scene addFlightScene, searchFlightScene, scene;

	public AccountPage(Customer customer) {

		this.customer = customer;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Account Page");

		
		
		
		//All Flights buttons
		allFlights.setOnAction(e -> {
			handleGetAllFlights(e);
			
			
		});
		
		GridPane.setConstraints(allFlights, 10, 100);
		
		
		
		
		
		addFlight.setOnAction(e -> {

			handleAddFlight(e);
		});

		GridPane.setConstraints(addFlight, 10, 200);

		TableView<Flight> table = new TableView<>();
		
		
	

		//Flight Name Column
		TableColumn<Flight, String> nameColumn = new TableColumn<>("Flight Name");
		nameColumn.setMinWidth(50);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		//Flight Number Column
		TableColumn<Flight, Integer> numColumn = new TableColumn<>("Flight Number");
		numColumn.setMinWidth(50);
		numColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

		//From City Column 
		TableColumn<Flight, String> fromCityColumn = new TableColumn<>("From");
		fromCityColumn.setMinWidth(50);
		fromCityColumn.setCellValueFactory(new PropertyValueFactory<>("fromCity"));
		
		
		//To City Column
		TableColumn<Flight, String> toCityColumn = new TableColumn<>("To");
		toCityColumn.setMinWidth(50);
		toCityColumn.setCellValueFactory(new PropertyValueFactory<>("toCity"));
		
		//Date Column
		TableColumn<Flight, Date> dateColumn = new TableColumn<>("Date");
		dateColumn.setMinWidth(50);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("datetime"));
		
		//Capacity column
		TableColumn<Flight, Integer> capacityColumn = new TableColumn<>("Capacity");
		capacityColumn.setMinWidth(50);
		capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		
		
		//Status Column
		TableColumn<Flight, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(50);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		//Seats Booked Column
		TableColumn<Flight, Integer> bookedColumn = new TableColumn<>("Seats Booked");
		bookedColumn.setMinWidth(50);
		bookedColumn.setCellValueFactory(new PropertyValueFactory<>("seatsBooked"));

		
		//Gathers the data for the TableView
		table.setItems(GetFlights());
		table.getColumns().addAll(nameColumn, numColumn, fromCityColumn, toCityColumn, dateColumn, capacityColumn, statusColumn, bookedColumn);

	
	
		
		//Search Flights button
		searchFlight.setOnAction(e -> {
			
			handleSearch(e);
			
		});
		GridPane.setConstraints(searchFlight, 10, 400);

		
		//Make a new GridPane Object
		GridPane tablePane = new GridPane();
		
		//Stick the data in the object
		tablePane.getChildren().addAll(table, addFlight, searchFlight, logout, allFlights);
		
		
		//Show Scene 
		scene = new Scene(tablePane, 800, 800);
		scene.getRoot().setStyle("-fx-font-family: 'serif'");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		//Log out Button
		logout.setOnAction( e -> {
				
			primaryStage.close();
			
		});

		GridPane.setConstraints(logout, 10, 500);
	}

	
	
	
	

	// Get all the flights from Database
	public ObservableList<Flight> GetFlights() {
		ObservableList<Flight> flights = FXCollections.observableArrayList();
		flights.addAll(customer.getFlights());
		return flights;

	}

	
	//Add Flight Method
	public void handleAddFlight(ActionEvent addFlights) {

		final Stage ADD_FLIGHTS = new Stage();

		if (addFlights.getSource() == addFlight) {

			GridPane addFlightMenu = new GridPane();
			addFlightMenu.setPadding(new Insets(10, 10, 10, 10));
			addFlightMenu.setVgap(8);
			addFlightMenu.setHgap(10);

			// Firstname Label
			Label flightName = new Label("Flight Name");
			GridPane.setConstraints(flightName, 3, 1);

			// FirstName input
			TextField flightInput = new TextField();
			GridPane.setConstraints(flightInput, 3, 2);

			// LastName label
			Label flightNo = new Label("Flight Number");
			GridPane.setConstraints(flightNo, 3, 3);

			// LastName input.
			TextField flightNoInput = new TextField();
			GridPane.setConstraints(flightNoInput, 3, 4);

			// Address Label
			Label fromCity = new Label("From City");
			GridPane.setConstraints(fromCity, 3, 5);

			// Address input
			TextField fromCityInput = new TextField();
			GridPane.setConstraints(fromCityInput, 3, 6);

			// State Label
			Label toCity = new Label("To City");
			GridPane.setConstraints(toCity, 3, 7);

			// TO City Input
			TextField toCityInput = new TextField();
			GridPane.setConstraints(toCityInput, 3, 8);

			// Status label
			Label status = new Label("Flight Status");
			GridPane.setConstraints(status, 3, 9);

			// Status Input
			TextField statusInput = new TextField();
			GridPane.setConstraints(statusInput, 3, 10);

			// Capacity
			Label capacity = new Label("Flight Capacity");
			GridPane.setConstraints(capacity, 3, 11);

			// Status Input
			TextField capacityInput = new TextField();
			GridPane.setConstraints(capacityInput, 3, 12);

			Button submitFlight = new Button("Submit");
			GridPane.setConstraints(submitFlight, 10, 13);
			submitFlight.setOnAction(e -> {

				// Databse Connection
				addFlight(flightInput.getText(), flightNoInput.getText(), fromCityInput.getText(),
						toCityInput.getText(), statusInput.getText(), capacityInput.getText());
				
				
			});
			
			//Back button
			back.setOnAction(e -> {
				
				ADD_FLIGHTS.close();
				
			});
			GridPane.setConstraints(back,3, 13);

			addFlightMenu.getChildren().addAll(flightName, flightInput, flightNo, flightNoInput, fromCity,
					fromCityInput, toCity, toCityInput, status, statusInput, capacity, capacityInput, submitFlight, back);

			addFlightScene = new Scene(addFlightMenu, 900, 900);

			addFlightScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			ADD_FLIGHTS.setTitle("Add Flights");
			ADD_FLIGHTS.setScene(addFlightScene);
			ADD_FLIGHTS.show();
		}
	}

	public void addFlight(String flightName, String flightNumber, String fromCity, String toCity, String status,
			String capacity) {

		Flight flight = new Flight();

		flight.setName(flightName);
		flight.setNumber(Integer.valueOf(flightNumber));
		flight.setFromCity(fromCity);
		flight.setToCity(toCity);
		flight.setStatus(status);
		flight.setCapacity(Integer.valueOf(capacity));

		flight.setAction(Action.ADD_FLIGHT);

		boolean success = ExceptionHandler.process(flight);

		if (success) {
			System.out.println("Successful Added Flight!");

			customer.setAction(Action.GET_ALL_FLIGHTS);

			success = ExceptionHandler.process(customer);

			if (success) {

				FlightPage acc = new FlightPage(customer);

				try {

					acc.start(new Stage());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	public void handleSearch(ActionEvent Search) {
		
		final Stage searchF = new Stage();
		Button search = new Button("Search");
		Button back = new Button("Go Back");
		
		if(Search.getSource() == searchFlight) {
			
			GridPane searchFlightMenu = new GridPane();
			searchFlightMenu.setPadding(new Insets(10, 10, 10, 10));
			searchFlightMenu.setVgap(8);
			searchFlightMenu.setHgap(10);
			
			//From city label
			Label fromcity = new Label("From City");
			GridPane.setConstraints(fromcity, 1,1);
			
			// From cityTextField 
			TextField fromcityInput = new TextField();
			GridPane.setConstraints(fromcityInput, 1, 2);
			
			//To city label
			Label tocity = new Label("To City");
			GridPane.setConstraints(tocity, 1,3);
			
			//To City Textfield
			TextField tocityInput = new TextField();
			GridPane.setConstraints(tocityInput, 1, 4);
			
			//Search Button
			search.setOnAction(e -> {
				searchFlight(fromcityInput.getText(), tocityInput.getText());
				
			});
			GridPane.setConstraints(search, 2,6);
			
			//Back Button
			back.setOnAction(e -> {
				searchF.close();
				
			});
			
			
			GridPane.setConstraints(back, 1,6);
			
			
			searchFlightMenu.getChildren().addAll(fromcity, fromcityInput, tocity, tocityInput, search, back);
			searchFlightScene = new Scene(searchFlightMenu, 500, 500);
			
			searchFlightScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			searchF.setScene(searchFlightScene);
			searchF.setTitle("Search Flight");
			searchF.show();
			
		}
		
		
		
	}

	private void searchFlight(String fromcity, String tocity) {
		
		Flight flight = new Flight();
		//Customer c = new Customer();
		
		System.out.println("customerID" + customer.getCustomerID());
		System.out.println(fromcity);
		System.out.println(tocity);
		flight.setFromCity(fromcity);
		flight.setToCity(tocity);

		customer.setAction(Action.SEARCH_FLIGHTS);
		customer.setFlights(Arrays.asList(flight));
		

		boolean success = ExceptionHandler.process(customer);

		if (success) {
			System.out.println("Successful");



				FlightPage flightPage = new FlightPage(customer);

				try {

					flightPage.start(new Stage());

				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		
	}
	
	
	
	
	//All Flights Method
	public void handleGetAllFlights(ActionEvent allF) {
		customer.setAction(Action.GET_ALL_FLIGHTS);
		

		boolean success = ExceptionHandler.process(customer);

		if (success) {
			System.out.println("Successful");



				FlightPage flightPage = new FlightPage(customer);

				try {

					flightPage.start(new Stage());

				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		
	}
	
	
	

}
