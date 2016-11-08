package com.github.maximkirko.testing.services;

import com.github.maximkirko.testing.datamodel.models.Question;

public interface IQuestionToAnswerService {

	void save(Question question);

	void deleteByQuizId(Long id);

	void deleteByQuestionId(Long id);
}
