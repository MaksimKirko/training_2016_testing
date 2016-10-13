package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

public class Question extends AbstractModel {
    private String text;
    private Answer answer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
