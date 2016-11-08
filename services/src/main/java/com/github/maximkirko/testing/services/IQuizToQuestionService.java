package com.github.maximkirko.testing.services;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface IQuizToQuestionService {

	void save(Quiz quiz);

	void deleteByQuizId(Long id);

	void deleteByQuestionId(Long id);

}
