package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

public class Test extends AbstractModel {
	private String title;
	private Subject subjectl;
	private List<Question> questions;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Subject getSubjectl() {
		return subjectl;
	}

	public void setSubjectl(Subject subjectl) {
		this.subjectl = subjectl;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}	
}