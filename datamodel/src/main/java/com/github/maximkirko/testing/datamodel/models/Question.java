package com.github.maximkirko.testing.datamodel.models;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(name = "question")
public class Question extends AbstractModel {
	private String text;
	private String hint;

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

	@Override
	public String toString() {
		return "Question [text=" + text + ", hint=" + hint + "]";
	}

	public Question() {

	}
}