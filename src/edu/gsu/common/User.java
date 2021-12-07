package edu.gsu.common;

public abstract class User {
	private String userName;
	private String passWord;
	private String type; // user is a type of customer or admin

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public User(String userName, String passWord, String type) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User() {

	}	

}
