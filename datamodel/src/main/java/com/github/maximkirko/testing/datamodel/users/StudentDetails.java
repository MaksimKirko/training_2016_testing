package com.github.maximkirko.testing.datamodel.users;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;

@DBTable(name = "student_details")
public class StudentDetails extends AbstractModel {
	
	private Integer age;
	private Integer course;
	private String email;
	private String password;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
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
		return "StudentDetails [age=" + age + ", course=" + course + ", email=" + email + ", password=" + password + "]";
	}

	public StudentDetails() {

	}
}
