package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(name = "quiz")
public class Quiz extends AbstractModel {

	private String title;
	private String description;
	private Subject subject;
	private List<Question> questions;
	private List<Grade> grades;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Quiz [title=" + title + ", description=" + description + ", subject=" + subject + "]";
	}

}