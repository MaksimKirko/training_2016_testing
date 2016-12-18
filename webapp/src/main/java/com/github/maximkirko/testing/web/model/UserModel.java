package com.github.maximkirko.testing.web.model;

import java.util.List;

public class UserModel implements WebModel {

	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String course;
	private String email;
	private String password;
	private RoleModel role;
	private List<GradeModel> grades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public List<GradeModel> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeModel> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", course=" + course
				+ ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
}
