package com.github.maximkirko.testing.web.model;

import java.util.List;

public class SubjectModel implements WebModel {

	private Long id;
	private String title;
	private String description;
	private List<QuizModel> quizzes;

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

	public List<QuizModel> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<QuizModel> quizzes) {
		this.quizzes = quizzes;
	}

	@Override
	public String toString() {
		return "Subject [title=" + title + ", description=" + description + "]";
	}

}
