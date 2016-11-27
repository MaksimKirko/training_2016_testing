package com.github.maximkirko.testing.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

public interface IQuizToQuestionService {

	List<QuizToQuestion> getByQuiz(Quiz quiz);

	List<QuizToQuestion> getByQuestion(Question question);

	void save(QuizToQuestion quizToQuestion);

	@Transactional
	void saveAll(List<QuizToQuestion> quizToQuestions);

	@Transactional
	void deleteByQuiz(Quiz quiz);

	@Transactional
	void deleteByQuestion(Question question);

}
