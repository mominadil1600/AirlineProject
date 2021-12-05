package edu.gsu.gui;

import edu.gsu.common.Action;
import edu.gsu.common.Customer;
import edu.gsu.common.Registration;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginPage extends Application {

	Button registerButton = new Button("Register");
	Button loginButton = new Button("Login");
	Button back = new Button("Back");
	Button Submit = new Button("Submit");

	Scene scene2;
	Stage window;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a GridPane and set its properties
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setVgap(8);
		pane.setHgap(10);

		// Button loginButton = new Button("Login");
		TextField userName = new TextField();
		GridPane.setConstraints(userName, 1, 1);
		TextField password = new TextField();
		GridPane.setConstraints(password, 1, 3);

		loginButton.setOnAction(e -> login(userName.getText(), password.getText()));
		GridPane.setConstraints(loginButton, 1, 5);

		registerButton.setOnAction(e -> handle(e));
		GridPane.setConstraints(registerButton, 2, 5);

		pane.getChildren().addAll(userName, password, loginButton, registerButton);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 500, 500);
		// scene.getRoot().setStyle("-fx-font-family: 'serif'");

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setTitle("BookTickets"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public void login(String userName, String password) {

		Customer customer = new Customer();

		customer.setUserName(userName);
		customer.setPassWord(password);
		customer.setAction(Action.LOGIN);

		System.out.println("Customer:" + userName + " " + password);

		boolean success = ExceptionHandler.process(customer);

		if (success) {
			System.out.println("Successful Login!");

			customer.setAction(Action.GET_FLIGHTS);

			success = ExceptionHandler.process(customer);

			if (success) {

				Stage stage = (Stage) loginButton.getScene().getWindow();

				stage.close();

				AccountPage acc = new AccountPage(customer);

				try {

					acc.start(new Stage());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void registration(String firstName, String lastName, String address, String zip, Object state,
			String username, String password, String email, String ssn, Object securityQ, String answer) {

		Customer customer = new Customer();
		Registration r = new Registration();

		r.setFirstName(firstName);
		r.setLastName(lastName);
		r.setAddress(address);
		r.setZip(Integer.valueOf(zip));
		r.setState(state.toString());
		r.setUsername(username);
		r.setPassword(password);
		r.setEmail(email);
		r.setSsn(ssn);
		r.setSecurityQ(securityQ.toString());
		r.setAnswer(answer);

		customer.setAction(Action.REGISTER);
		customer.setRegistration(r);

		System.out.println("Customer:" + firstName + " " + password);

		boolean success = ExceptionHandler.process(customer);

		if (success) {
			System.out.println("Successful Registration!");

		}

	}

	public void handle(ActionEvent Register) {

		final Stage REGISTER = new Stage();

		if (Register.getSource() == registerButton) {

			GridPane rMenu = new GridPane();
			rMenu.setPadding(new Insets(10, 10, 10, 10));
			rMenu.setVgap(8);
			rMenu.setHgap(10);

			// Firstname Label
			Label firstname = new Label("First Name");
			GridPane.setConstraints(firstname, 3, 1);

			// FirstName input
			TextField first = new TextField();
			GridPane.setConstraints(first, 3, 2);

			// LastName label
			Label lastname = new Label("Last Name");
			GridPane.setConstraints(lastname, 3, 3);

			// LastName input.
			TextField last = new TextField();
			GridPane.setConstraints(last, 3, 4);

			// Address Label
			Label address = new Label("Address");
			GridPane.setConstraints(address, 3, 5);

			// Address input
			TextField addressInput = new TextField();
			GridPane.setConstraints(addressInput, 3, 6);

			// State Label
			Label stateLabel = new Label("State");

			GridPane.setConstraints(stateLabel, 3, 7);

			// Drop down Menu for state
			@SuppressWarnings("rawtypes")
			ComboBox state = new ComboBox<>();
			state.getItems().add("GA");
			state.getItems().add("FL");
			state.getItems().add("AL");
			state.getItems().add("NY");
			state.getItems().add("CA");

			GridPane.setConstraints(state, 4, 7);

			// City Label
			Label City = new Label("City");
			GridPane.setConstraints(City, 3, 8);

			// City input
			TextField cityInput = new TextField();
			GridPane.setConstraints(cityInput, 3, 9);

			// Zip code label
			Label zip = new Label("Zipcode");
			GridPane.setConstraints(zip, 3, 10);

			// Zipcode input
			TextField codeInput = new TextField();
			GridPane.setConstraints(codeInput, 3, 11);

			// Username label
			Label userName = new Label("Username");
			GridPane.setConstraints(userName, 3, 12);

			// Username inoput
			TextField user = new TextField();
			GridPane.setConstraints(user, 3, 13);

			// Password label
			Label password = new Label("Password");
			GridPane.setConstraints(password, 3, 14);

			// Password input
			TextField passWord = new TextField();
			GridPane.setConstraints(passWord, 3, 15);

			// Email label
			Label email = new Label("Email");
			GridPane.setConstraints(email, 3, 16);

			// Email Input
			TextField emailInput = new TextField();
			GridPane.setConstraints(emailInput, 3, 17);

			// SSN label
			Label ssn = new Label("Social Security Number");
			GridPane.setConstraints(ssn, 3, 18);

			// SSN input
			TextField ssnInput = new TextField();
			GridPane.setConstraints(ssnInput, 3, 19);

			// Security Question label
			Label security = new Label("Security Question");
			GridPane.setConstraints(security, 3, 20);

			// Security Question Dropdown
			@SuppressWarnings("rawtypes")
			ComboBox securityQ = new ComboBox<>();
			securityQ.getItems().add("What was the name of your first pet? ");
			securityQ.getItems().add("What is your mothers maiden name? ");

			GridPane.setConstraints(securityQ, 3, 21);

			// Answer to security question label and input
			Label answer = new Label("Answer");
			GridPane.setConstraints(answer, 3, 22);

			TextField answerInput = new TextField();
			GridPane.setConstraints(answerInput, 3, 23);

			// Back button layout
			back = new Button("Back");
			back.setOnAction(e -> {

				REGISTER.close();
				window.show();
			});
			GridPane.setConstraints(back, 1, 30);

			// Registration Submit Button
			Submit = new Button("Submit");
			Submit.setOnAction(e -> {

				registration(first.getText(), last.getText(), addressInput.getText(), codeInput.getText(),
						state.getValue(), user.getText(), passWord.getText(), emailInput.getText(), ssnInput.getText(),
						securityQ.getValue(), answerInput.getText());

				REGISTER.close();

			});

			GridPane.setConstraints(Submit, 20, 30);

			// Make window
			rMenu.getChildren().addAll(firstname, first, lastname, last, address, addressInput, stateLabel, state, City,
					cityInput, zip, codeInput, userName, user, password, passWord, ssn, ssnInput, security, securityQ,
					answer, answerInput, email, emailInput, Submit, back);

			scene2 = new Scene(rMenu, 900, 900);

			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			REGISTER.setTitle("Registration Page");
			REGISTER.setScene(scene2);
			REGISTER.show();
		}
	}

}
