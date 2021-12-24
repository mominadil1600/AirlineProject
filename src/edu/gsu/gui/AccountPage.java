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
	Button logout = new Button("Logout");
	Button allFlights = new Button("All Flights");
	Scene addFlightScene, searchFlightScene, scene;

	
	public AccountPage(Customer customer) {

		this.customer = customer;
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Account Page");


		//Table View for flights added in account
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

	
		
	
		
		//All Flights Button
		allFlights.setOnAction(e -> {
			
			handleGetAllFlights(e);
	
		});
		
		GridPane.setConstraints(allFlights, 10, 100);
		
		
		

		
		
		
		
		
		


		
		//Make a new GridPane Object
		GridPane tablePane = new GridPane();
		
		//Stick the data in the object
		tablePane.getChildren().addAll(table, logout, allFlights);
		
		
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
