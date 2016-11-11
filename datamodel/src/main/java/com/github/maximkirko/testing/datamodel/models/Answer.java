package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(name = "answer")
public class Answer extends AbstractModel {
	private String text;
	private List<Question> questions;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Answer [text=" + text + "]";
	}

	public Answer() {
		
	}
}