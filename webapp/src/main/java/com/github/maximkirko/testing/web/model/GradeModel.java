package com.github.maximkirko.testing.web.model;

public class GradeModel implements WebModel {

	private Long id;
	private float mark;
	private UserModel user;
	private QuizModel quiz;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public QuizModel getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizModel quiz) {
		this.quiz = quiz;
	}

	@Override
	public String toString() {
		return "Grade [mark=" + mark + ", user=" + user + ", quiz=" + quiz + "]";
	}
}
