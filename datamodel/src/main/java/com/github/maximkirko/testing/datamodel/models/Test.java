package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

import com.github.maximkirko.testing.datamodel.AbstractModel;

public class Test extends AbstractModel {
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}	
}
