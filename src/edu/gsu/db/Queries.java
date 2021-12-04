package edu.gsu.db;

public class Queries {
	
	
	//Query for log in access
	public static final String LOGIN = "SELECT count(*) as num  FROM user WHERE username =? AND password =?";
	
	
	//Query for Regsitering  (NOT FINAL QUERY STILL WORKING ON IT)
	
	/*
	 * NEED AN INSERT QUERY
	 * I DONT KNOW IF THIS IS RIGHT
	 * |
	 * V
	 */
	
	public static final String REGISTRATION = " INSERT INTO registration VALUES (firstname =? AND lastname =? AND address =? AND zip =? AND state =? AND email =? and securityQ =? AND answer =?) ";


			
}
