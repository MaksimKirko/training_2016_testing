package com.github.maximkirko.testing.datamodel.models;

public class Answer extends AbstractModel {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Answer [text=" + text + "]";
	}

	public Answer() {
		
	}
}