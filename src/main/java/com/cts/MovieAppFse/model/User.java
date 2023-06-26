package com.cts.MovieAppFse.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserDetails")
public class User {
	@Id
	
	private int loginid;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String contactno;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int loginid, String firstName, String lastName, String email, String password, String contactno) {
		super();
		this.loginid = loginid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contactno = contactno;
	}
	@Override
	public String toString() {
		return "User [loginid=" + loginid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", contactno=" + contactno + "]";
	}
	public int getLoginid() {
		return loginid;
	}
	public void setLoginid(int loginid) {
		this.loginid = loginid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	
	
	
	
}
