package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface IQuizService extends IGenericService<Quiz, Long> {

	Quiz getWithSubject(Long id);

	List<Quiz> getBySubjectId(Long id);

	Quiz getWithQuestions(Long id);
}
