package com.github.maximkirko.testing.daodb.customentity;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public class QuizWithSubject {

	private Quiz quiz;
	private Subject subject;

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
