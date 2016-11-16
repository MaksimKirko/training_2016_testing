package com.github.maximkirko.testing.daodb.customentity;

import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@DBTable(name = "quiz_2_question")
public class QuizToQuestion {

	private Quiz quiz;
	private Question question;

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public static List<QuizToQuestion> quizToQTQList(Quiz quiz) {

		List<QuizToQuestion> quizToQuestions = new ArrayList<QuizToQuestion>();
		for (Question question : quiz.getQuestions()) {

			QuizToQuestion quizToQuestion = new QuizToQuestion();

			quizToQuestion.setQuiz(quiz);
			quizToQuestion.setQuestion(question);

			quizToQuestions.add(quizToQuestion);
		}

		return quizToQuestions;
	}
	
	public static List<QuizToQuestion> questionToQTQList(Question question) {

		List<QuizToQuestion> quizToQuestions = new ArrayList<QuizToQuestion>();
		for (Quiz quiz : question.getQuizzes()) {

			QuizToQuestion quizToQuestion = new QuizToQuestion();

			quizToQuestion.setQuiz(quiz);
			quizToQuestion.setQuestion(question);

			quizToQuestions.add(quizToQuestion);
		}

		return quizToQuestions;
	}
	
}
