package com.github.maximkirko.testing.datamodel.users;

import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;
import com.github.maximkirko.testing.datamodel.models.Grade;

@DBTable(name = "student")
public class Student extends AbstractModel {

	private String firstName;
	private String lastName;
	private Integer age;
	private Integer course;
	private String email;
	private String password;
	private List<Grade> grades;

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

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", course=" + course
				+ ", email=" + email + ", password=" + password + "]";
	}

	public Student() {

	}
}
