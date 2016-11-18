package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

public interface IQuizToQuestionDao {

	List<QuizToQuestion> getByQuiz(Quiz quiz);

	List<QuizToQuestion> getByQuestion(Question question);

	void insert(QuizToQuestion entity);

	void deleteByQuiz(Quiz quiz);

	void deleteByQuestion(Question question);

}
