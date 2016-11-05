package com.github.maximkirko.testing.datamodel.users;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public class User extends AbstractModel {
	
	private String firstName;
	private String lastName;
	
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
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	public User() {
		
	}
}
