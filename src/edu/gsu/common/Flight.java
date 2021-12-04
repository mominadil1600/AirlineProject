package edu.gsu.common;

import java.util.Date;

public class Flight {
	static int seatsBooked;

	private int fid;
	private String name;
	private int number;
	private String fromCity;
	private String toCity;
	private int capacity;
	private String status;
	private Date datetime;
	private String action;

	public Flight() {

	}

	public Flight(String name, int number, String fromCity, String toCity, int capacity, String status) {
		super();
		this.name = name;
		this.number = number;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.capacity = capacity;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public static int getSeatsBooked() {
		return seatsBooked;
	}

	public static void setSeatsBooked(int seatsBooked) {
		Flight.seatsBooked = seatsBooked;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	
}
