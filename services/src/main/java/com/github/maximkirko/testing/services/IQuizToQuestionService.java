package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;

public interface IQuizToQuestionService {

	List<QuizToQuestion> getByQuiz(Long id);
	
	List<QuizToQuestion> getByQuestion(Long id);

	void save(QuizToQuestion quizToQuestion);

	void saveAll(List<QuizToQuestion> quizToQuestions);

	void deleteByQuizId(Long id);

	void deleteByQuestionId(Long id);

}
