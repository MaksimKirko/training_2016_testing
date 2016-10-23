package com.github.maximkirko.testing.datamodel.models;

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

	public Question(String text, String hint) {
		this.text = text;
		this.hint = hint;
	}
}