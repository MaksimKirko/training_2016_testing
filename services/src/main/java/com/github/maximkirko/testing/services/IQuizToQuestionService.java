package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

public interface IQuizToQuestionService {

	List<QuizToQuestion> getByQuiz(Quiz quiz);
	
	List<QuizToQuestion> getByQuestion(Question question);

	void save(QuizToQuestion quizToQuestion);

	void saveAll(List<QuizToQuestion> quizToQuestions);

	void deleteByQuiz(Quiz quiz);

	void deleteByQuestion(Question question);

}
