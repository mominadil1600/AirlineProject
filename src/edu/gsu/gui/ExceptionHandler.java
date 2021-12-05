package edu.gsu.gui;

import edu.gsu.bizlogic.BizLogicProcess;
import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionHandler {

	public static boolean process(Customer co) {
		
		try {
			
			BizLogicProcess.processCustomer(co);
			
		} catch (Exception ex) {
			
			  Alert alert = new Alert(AlertType.ERROR);
			  alert.setContentText(ex.getMessage());
	      
			  
			  alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
             

			  alert.showAndWait();
			  return false;
		}
		
		return true;
		
		
		
	}
	
	
public static boolean processFlight(Flight flight) {
		
		try {
			
			BizLogicProcess.processFlight(flight);
			
		} catch (Exception ex) {
			
			  Alert alert = new Alert(AlertType.ERROR);
			  //alert.setTitle("Login Dialog");
			  //alert.setHeaderText("Look, an Information Dialog");
			  alert.setContentText(ex.getMessage());
	      
			  
			  alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
             

			  alert.showAndWait();
			  return false;
		}
		
		return true;
		
		
		
	}
	
}
