package com.github.maximkirko.testing.datamodel.models;

import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.annotations.Filename;

@DBTable("question")
@Filename("question.xml")
public class Question extends AbstractModel {
	
	private String text;
	private String hint;
	private List<Answer> answers;
	private List<Quiz> quizzes;
	
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
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	@Override
	public String toString() {
		return "Question [text=" + text + ", hint=" + hint + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((hint == null) ? 0 : hint.hashCode());
		result = prime * result + ((quizzes == null) ? 0 : quizzes.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (hint == null) {
			if (other.hint != null)
				return false;
		} else if (!hint.equals(other.hint))
			return false;
		if (quizzes == null) {
			if (other.quizzes != null)
				return false;
		} else if (!quizzes.equals(other.quizzes))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	public Question() {
		answers = new ArrayList<Answer>();
	}
}