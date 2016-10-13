package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

import com.github.maximkirko.testing.datamodel.AbstractModel;

public class Question extends AbstractModel {
    private String text;
    private List<String> answers;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
