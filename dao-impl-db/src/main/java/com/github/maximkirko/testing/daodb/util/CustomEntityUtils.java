package com.github.maximkirko.testing.daodb.util;

import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;

public class CustomEntityUtils {

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

	public static List<QuestionToAnswer> questionQTAList(Question question) {

		List<QuestionToAnswer> questionToAnswers = new ArrayList<QuestionToAnswer>();
		for (Answer answer : question.getAnswers()) {

			QuestionToAnswer questionToAnswer = new QuestionToAnswer();

			questionToAnswer.setQuestion(question);
			questionToAnswer.setAnswer(answer);

			questionToAnswers.add(questionToAnswer);
		}

		return questionToAnswers;
	}

}
