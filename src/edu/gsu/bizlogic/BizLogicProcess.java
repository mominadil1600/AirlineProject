package edu.gsu.bizlogic;

import edu.gsu.common.Action;
import edu.gsu.common.Customer;
import edu.gsu.common.Flight;
import edu.gsu.db.DBQueries;

public class BizLogicProcess {

	/**
	 * process customer operations
	 * 
	 * @param co
	 * @throws Exception
	 */
	public static void process(Customer co) throws Exception {

		switch (co.getAction()) {
		case Action.LOGIN:
			DBQueries.login(co);
			break;
		case Action.BOOK_TICKETS:
			DBQueries.bookTicket(co.getCustomerID(), co.getFlights().get(0));
			break;
		case Action.GET_FLIGHTS:
			DBQueries.getFlights(co);
			break;
		case Action.REGISTER:
			DBQueries.registration(co.getRegistration(), "Customer");
			break;
		case Action.RECOVER_PASSWORD:
			DBQueries.recoverPassword(co);
			break;
		case Action.SEARCH_FLIGHTS:
			DBQueries.searchFlights(co);
			break;
		case Action.GET_ALL_FLIGHTS:
			DBQueries.getAllFlights(co);
			break;

		}
	}

	/**
	 * process flight operations
	 * 
	 * @param flight
	 * @throws Exception
	 */
	public static void process(Flight flight) throws Exception {

		switch (flight.getAction()) {
		case Action.ADD_FLIGHT:
			DBQueries.addFlight(flight);
			break;
		case Action.DELETE_FLIGHT:
			DBQueries.deleteFlight(flight);
			break;

		}
	}

}
