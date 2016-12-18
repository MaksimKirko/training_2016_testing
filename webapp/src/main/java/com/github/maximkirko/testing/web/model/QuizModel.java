package com.github.maximkirko.testing.web.model;

import java.util.List;

public class QuizModel implements WebModel {

	private Long id;
	private String title;
	private String description;
	private SubjectModel subject;
	private List<QuestionModel> questions;
	private List<GradeModel> grades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public SubjectModel getSubject() {
		return subject;
	}

	public void setSubject(SubjectModel subject) {
		this.subject = subject;
	}

	public List<QuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}

	public List<GradeModel> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeModel> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Quiz [title=" + title + ", description=" + description + ", subject=" + subject + "]";
	}
}
