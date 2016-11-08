package com.github.maximkirko.testing.datamodel.models;

import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(name = "question")
public class Question extends AbstractModel {
	private String text;
	private String hint;
	private List<Answer> answers;
	
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

	public Question() {
		answers = new ArrayList<Answer>();
	}
}