package edu.gsu.bizlogic;

import edu.gsu.common.Action;
import edu.gsu.common.Customer;
import edu.gsu.db.DBQueries;

public class BizLogicProcess {
	
	public static void process(Customer co) throws Exception {
		
		switch (co.getAction()) {
		
			case Action.LOGIN:
			
				DBQueries.login(co);
				break;
			case Action.GET_FLIGHTS:
				DBQueries.getFlights(co);
				break;
		    
		}
	}
	
	
	
	
	

}
