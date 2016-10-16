package com.github.maximkirko.testing.datamodel.models;

public class Subject extends AbstractModel {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject() {
	}
	
	public Subject(String name) {
		this.name = name;
	}
}
