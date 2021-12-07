package edu.gsu.common;

public class Registration {

	private String firstName;
	private String lastName;
	private String address;
	private int zip;
	private String state;
	private String username;
	private String password;
	private String email;
	private String ssn;
	private String securityQ;
	private String answer;

	public Registration() {

	}

	public Registration(String firstName, String lastName, String address, int zip, String state, String username,
			String password, String email, String ssn, String securityQ, String answer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.username = username;
		this.password = password;
		this.email = email;
		this.ssn = ssn;
		this.securityQ = securityQ;
		this.answer = answer;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityQ() {
		return securityQ;
	}

	public void setSecurityQ(String securityQ) {
		this.securityQ = securityQ;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answerQ) {
		this.answer = answerQ;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

}
