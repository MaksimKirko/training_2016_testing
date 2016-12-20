package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

public interface IQuizToQuestionDao {

	List<QuizToQuestion> getByQuizId(Long id);

	List<QuizToQuestion> getByQuestionId(Long id);

	void insert(QuizToQuestion entity);

	void deleteByQuizId(Long id);

	void deleteByQuestionId(Long id);

}
