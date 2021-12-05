package edu.gsu.gui;

import edu.gsu.common.Action;
import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccountPage extends Application {

	
		private Customer customer;
		Button addFlight = new Button("Add Flight");
		Button deleteFlight = new Button("Delete Flight");
		Button submitFlight = new Button("Submit");
		Scene addFlightScene;
	  
	public AccountPage(Customer customer) {
		
		this.customer = customer;
	}
	  
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Flights");
        

        
        
        
        
       
        addFlight.setOnAction(e -> {
        	
        	handleAddFlight(e);
        });
        
        GridPane.setConstraints(addFlight, 1, 1);
        
        TableView<Flight> table = new TableView<>();
        
        TableColumn<Flight, String> nameColumn = new TableColumn<>("Flight Name");
        nameColumn.setMinWidth(50);
        nameColumn.setCellValueFactory(new PropertyValueFactory<> ("name"));
        
        
        TableColumn<Flight, Integer> numColumn = new TableColumn<>("Flight Number");
        numColumn.setMinWidth(50);
        numColumn.setCellValueFactory(new PropertyValueFactory<> ("number"));
        
        
        TableColumn<Flight, String> fromCityColumn = new TableColumn<>("From City");
        fromCityColumn.setMinWidth(50);
        fromCityColumn.setCellValueFactory(new PropertyValueFactory<> ("fromCity"));
        
        table.setItems(GetFlights());
        table.getColumns().addAll(nameColumn,numColumn, fromCityColumn );
        
        
        
        deleteFlight.setOnAction(event -> {
            ObservableList<Integer> selectedIndices = table.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                System.out.println("Index = " + o + " (" + o.getClass() + ")");
                System.out.println(table.getItems().get((Integer)o).getFid());
                deleteFlight(table.getItems().get((Integer)o));
            }
        });
        
       GridPane.setConstraints(deleteFlight, 2, 1);
        
        
        GridPane vBox = new GridPane();
        vBox.setPadding(new Insets(10,10,10,10));
       
        
        
        vBox.getChildren().addAll(table, deleteFlight, addFlight);

        Scene scene = new Scene(vBox, 700, 700);
		scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    
    
    public void deleteFlight(Flight flight)  {
  	  
  	  
  	 
  	 flight.setAction(Action.DELETE_FLIGHTS);
  	 //customer.getFlights().add(flight);
  	  
  	 
  	  
  	  
  	  boolean success = ExceptionHandler.processFlight(flight);
  	  
  	  if (success) {
  		  
  		  customer.setAction(Action.GET_FLIGHTS);
  		  
  		  success = ExceptionHandler.process(customer);
  		  
  		  if (success) {
  			 AccountPage acc = new AccountPage(customer);
			  
			  try {
				  
				  acc.start(new Stage());
				  
				  
			  } catch (Exception e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  } 
  		  }
  	  }
  	  
    } 
    
    //Get all the flights
    //Connect this method to a database
  public ObservableList<Flight> GetFlights(){
    	ObservableList<Flight> flights =  FXCollections.observableArrayList();
    	
    	flights.addAll(customer.getFlights());
    	
    	
    	
    	
    	
    	return flights;
    	
    }
  
  





  public void handleAddFlight(ActionEvent addFlights) {
		
		final Stage ADD_FLIGHTS = new Stage();
		
		
		if(addFlights.getSource() == addFlight) {
			
		
		
		GridPane addFlightMenu = new GridPane();
		addFlightMenu.setPadding(new Insets(10,10,10,10));
		addFlightMenu.setVgap(8);
		addFlightMenu.setHgap(10);
		
		
		
		//Firstname Label
		Label flightName = new Label("Flight Name");
		GridPane.setConstraints(flightName, 3, 1);
		
		//FirstName input
		TextField flightInput = new TextField();
		GridPane.setConstraints(flightInput, 3, 2);
		
		//LastName label
		Label flightNo = new Label("Flight Number");
		GridPane.setConstraints(flightNo, 3, 3);
		
		//LastName input.
		TextField flightNoInput = new TextField();
		GridPane.setConstraints(flightNoInput, 3, 4);
		
		
		//Address Label
		Label fromCity = new Label("From City");
		GridPane.setConstraints(fromCity, 3,5);
		
		//Address input
		TextField fromCityInput = new TextField();
		GridPane.setConstraints(fromCityInput, 3, 6);
		
		//State Label 
		Label toCity = new Label("To City");
		GridPane.setConstraints(toCity,3,7);
		
				
		//TO City Input
		TextField toCityInput = new TextField();
		GridPane.setConstraints(toCityInput, 3 , 8);
		
		//Status label
		Label status = new Label("Flight Status");
		GridPane.setConstraints(status, 3, 9);
		
		//Status Input
		TextField statusInput = new TextField();
		GridPane.setConstraints(statusInput,3 ,10);
		
		//Capacity
		Label capacity = new Label("Flight Capacity");
		GridPane.setConstraints(capacity, 3, 11);
		
		//Status Input
		TextField capacityInput = new TextField();
		GridPane.setConstraints(capacityInput,3 ,12);
		
		
		
		
		
		
	
		Button submitFlight = new Button("Submit");
		GridPane.setConstraints(submitFlight, 3, 13);
		submitFlight.setOnAction(e -> {
			
			//Databse
			addFlight(flightInput.getText(), flightNoInput.getText(), fromCityInput.getText(), 
						toCityInput.getText(),statusInput.getText(), capacityInput.getText());
			
			
			
		});
								
		
		
		addFlightMenu.getChildren().addAll(flightName, flightInput, flightNo, flightNoInput, fromCity, 
				fromCityInput, toCity, toCityInput, status, statusInput,capacity, capacityInput ,submitFlight);
		
		addFlightScene = new Scene(addFlightMenu, 900, 900);
		
		
		
		
		addFlightScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		ADD_FLIGHTS.setTitle("Add Flights");
		ADD_FLIGHTS.setScene(addFlightScene);
		ADD_FLIGHTS.show();
		}	
	}    
    
    
    
    
  
  
  
public void addFlight(String flightName, String flightNumber, String fromCity, String toCity, String status, String capacity)  {
	  
	  Flight flight = new Flight();
	  
	 flight.setName(flightName);
	 flight.setNumber(Integer.valueOf(flightNumber));
	 flight.setFromCity(fromCity);
	 flight.setToCity(toCity);
	 flight.setStatus(status);
	 flight.setCapacity(Integer.valueOf(capacity));
	 
	 flight.setAction(Action.ADD_FLIGHTS);
	 //customer.getFlights().add(flight);
	  
	 
	  
	  
	  boolean success = ExceptionHandler.processFlight(flight);
	  
	  if (success) {
		  System.out.println("Successful Added Flight!");
		  
		  customer.setAction(Action.GET_FLIGHTS);
		  
		  success = ExceptionHandler.process(customer);
		  
		  if (success) {
	  
			
		  
			  AccountPage acc = new AccountPage(customer);
			  
			  try {
				  
				  acc.start(new Stage());
				  
				  
			  } catch (Exception e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  } 
		  }
	  }
	  
  }
  
  
  
  
  

    
}

