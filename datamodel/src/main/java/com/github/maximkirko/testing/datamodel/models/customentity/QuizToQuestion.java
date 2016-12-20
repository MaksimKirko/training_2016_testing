package com.github.maximkirko.testing.datamodel.models.customentity;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(value = "quiz_2_question")
public class QuizToQuestion {

	private Long quizId;
	private Long questionId;

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public QuizToQuestion(Long quizId, Long questionId) {
		this.quizId = quizId;
		this.questionId = questionId;
	}

	public QuizToQuestion() {

	}

	// public static List<QuizToQuestion> quizToQTQList(Quiz quiz) {
	//
	// List<QuizToQuestion> quizToQuestions = new ArrayList<QuizToQuestion>();
	// for (Question question : quiz.getQuestions()) {
	//
	// QuizToQuestion quizToQuestion = new QuizToQuestion();
	//
	// quizToQuestion.setQuiz(quiz);
	// quizToQuestion.setQuestion(question);
	//
	// quizToQuestions.add(quizToQuestion);
	// }
	//
	// return quizToQuestions;
	// }
	//
	// public static List<QuizToQuestion> questionToQTQList(Question question) {
	//
	// List<QuizToQuestion> quizToQuestions = new ArrayList<QuizToQuestion>();
	// for (Quiz quiz : question.getQuizzes()) {
	//
	// QuizToQuestion quizToQuestion = new QuizToQuestion();
	//
	// quizToQuestion.setQuiz(quiz);
	// quizToQuestion.setQuestion(question);
	//
	// quizToQuestions.add(quizToQuestion);
	// }
	//
	// return quizToQuestions;
	// }

}
