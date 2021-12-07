package edu.gsu.gui;

import edu.gsu.bizlogic.BizLogicProcess;
import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionHandler {

	public static boolean process(Customer co) {

		try {
			BizLogicProcess.process(co);

		} catch (Exception ex) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(ex.getMessage());
			alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
			alert.showAndWait();
			return false;
		}

		return true;

	}

	public static boolean process(Flight flight) {

		try {

			BizLogicProcess.process(flight);

		} catch (Exception ex) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Error Deleting Flight! Someone Has Booked a Ticket. ");
			alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
			alert.showAndWait();
			return false;
		}

		return true;

	}

}
