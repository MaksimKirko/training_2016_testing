package com.github.maximkirko.testing.datamodel.users;

public class Tutor {
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Tutor [password=" + password + "]";
	}

	public Tutor(String password) {
		this.password = password;
	}
	
	public void addQuiz() {
		
	}
	
	public void editQuiz() {
		
	}
	
	public void deleteQuiz() {
		
	}
	
	public Tutor() {
		
	}
}
