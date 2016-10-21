package com.github.maximkirko.testing.datamodel.models;

public class Subject extends AbstractModel {
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Subject(String name) {
		this.name = name;
	}
}
