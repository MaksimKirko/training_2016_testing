package com.github.maximkirko.testing.web.model;

public class AnswerModel implements WebModel {

	private Long id;
	private String text;
	private boolean correctness;
	private Long questionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrectness() {
		return correctness;
	}

	public void setCorrectness(boolean correctness) {
		this.correctness = correctness;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "AnswerModel [id=" + id + ", text=" + text + ", correctness=" + correctness + ", questionId="
				+ questionId + "]";
	}

}