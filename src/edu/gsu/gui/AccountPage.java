package edu.gsu.gui;

import edu.gsu.common.Action;
import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class AccountPage extends Application {

	
	private Customer customer;
	
	  
	public AccountPage(Customer customer) {
		
		this.customer = customer;
	}
	  
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Flights");
        

        ListView<String> listView = new ListView<>();

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (int i = 0; i < customer.getFlights().size(); i++) {
        
        	listView.getItems().add(customer.getFlights().get(i).getAirlineName());

        }

        Button button = new Button("Read Selected Value");

        button.setOnAction(event -> {
            ObservableList<Integer> selectedIndices = listView.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                System.out.println("Index = " + o + " (" + o.getClass() + ")");
                System.out.println(listView.getItems().get((Integer)o));
            }
        });


        VBox vBox = new VBox(listView, button);

        Scene scene = new Scene(vBox, 500, 420);
		scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    
    
    //Get all the flights
    //Connect this method to a database
  //  public ObservableList<Flight> GetFlights(){
    	
    	
    	
  //  }
    
    
    
    
    

    //public static void main(String[] args) {
    //    Application.launch(args);
    
	//} 
}

