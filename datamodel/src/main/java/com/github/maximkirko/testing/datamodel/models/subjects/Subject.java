package com.github.maximkirko.testing.datamodel.models.subjects;

import java.util.List;

import com.github.maximkirko.testing.datamodel.AbstractModel;
import com.github.maximkirko.testing.datamodel.models.Question;

public abstract class Subject extends AbstractModel {
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}	
}
