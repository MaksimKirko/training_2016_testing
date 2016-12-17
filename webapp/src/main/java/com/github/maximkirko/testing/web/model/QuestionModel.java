package com.github.maximkirko.testing.web.model;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Answer;

public class QuestionModel implements WebModel {
	
	private Long id;
	private String text;
	private String hint;
	private List<Answer> answers;
	
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

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [text=" + text + ", hint=" + hint + "]";
	}
}
