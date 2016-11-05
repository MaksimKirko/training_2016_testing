package com.github.maximkirko.testing.datamodel.users;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public class UserDetails extends AbstractModel {
	
	private Integer age;
	private String course;
	private String email;
	private String password;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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

	@Override
	public String toString() {
		return "UserDetails [age=" + age + ", course=" + course + ", email=" + email + ", password=" + password + "]";
	}

	public UserDetails() {

	}
}
