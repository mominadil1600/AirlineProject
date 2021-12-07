package edu.gsu.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gsu.common.Action;
import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FlightPage extends Application {

	private List<Flight> flights;
	Customer customer;
	Button ok = new Button("Ok");
	Button bookTickets = new Button("Book Ticket");
	Button deleteFlight = new Button("Delete Flight");

	Scene addFlightScene, searchFlightScene, scene;

	public FlightPage(List<Flight> flights) {

		this.flights = flights;
	}
	public FlightPage(Customer customer) {

		this.customer = customer;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(" Flight Details");

		

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

		
		
		//Ok Button Position and Action
		GridPane.setConstraints(ok, 10, 100);
		
		ok.setOnAction(e -> {
			
		primaryStage.close();
			
		});
		

		
		//Make a new GridPane Object
		GridPane tablePane = new GridPane();
		
		
		
		//Book Ticket 
				bookTickets.setOnAction(e -> {
					
					ObservableList<Integer> selectedIndices = table.getSelectionModel().getSelectedIndices();
					System.out.println(selectedIndices);
					
					if((selectedIndices.isEmpty())) {
						
						 Alert alert = new Alert(AlertType.ERROR);
						  //alert.setTitle("Login Dialog");
						  //alert.setHeaderText("Look, an Information Dialog");
						  alert.setContentText("Please Select a Flight to book a ticket!");
				      
						  
						  alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
			             

						  alert.showAndWait();
						
						
					}

					for (Object o : selectedIndices) {
						System.out.println("Index = " + o + " (" + o.getClass() + ")");
						System.out.println(table.getItems().get((Integer) o).getFid());
						bookFlight(table.getItems().get((Integer) o));
					}});
		
				GridPane.setConstraints(bookTickets, 10, 200);
		
		
				
				//Deletes Flight from Account 
				deleteFlight.setOnAction(event -> {
					ObservableList<Integer> selectedIndices = table.getSelectionModel().getSelectedIndices();

					for (Object o : selectedIndices) {
						System.out.println("Index = " + o + " (" + o.getClass() + ")");
						System.out.println(table.getItems().get((Integer) o).getFid());
						deleteFlight(table.getItems().get((Integer) o));
					}
						
				});
			
				GridPane.setConstraints(deleteFlight, 10,300);
				
				
		
		
		
		
		//Stick the data in the object
		tablePane.getChildren().addAll(table, ok, bookTickets,deleteFlight);
		
		
		//Show Scene 
		scene = new Scene(tablePane, 800, 800);
		scene.getRoot().setStyle("-fx-font-family: 'serif'");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	

	
	

	// Get all the flights from Database
	public ObservableList<Flight> GetFlights() {
		ObservableList<Flight> ObsFlights = FXCollections.observableArrayList();
		ObsFlights.addAll(customer.getFlights());
		return ObsFlights;

	}
	
	
private void bookFlight(Flight flight) {
		
		System.out.println("CustomerID " + customer.getCustomerID());
		flight.setAction(Action.BOOK_TICKETS);
		customer.setAction(Action.BOOK_TICKETS);
		
		customer.setFlights(new ArrayList<>());
		customer.getFlights().add(flight);
		

		boolean success = ExceptionHandler.process(customer);

		if (success) {

			customer.setAction(Action.GET_FLIGHTS);

			success = ExceptionHandler.process(customer);

			if (success) {
				AccountPage acc = new AccountPage(customer);

				try {

					acc.start(new Stage());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}


//Delete Flight Method
	public void deleteFlight(Flight flight) {

		flight.setAction(Action.DELETE_FLIGHT);

		boolean success = ExceptionHandler.process(flight);

		if (success) {

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
	






}
