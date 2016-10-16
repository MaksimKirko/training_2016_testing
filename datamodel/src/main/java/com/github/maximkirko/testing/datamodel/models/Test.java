package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

public class Test extends AbstractModel {
	private String title;
	private List<Subject> subject;
	private List<Question> questions;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Test() {

	}

	public Test(String title, List<Subject> subject, List<Question> questions) {
		this.title = title;
		this.subject = subject;
		this.questions = questions;
	}	
}