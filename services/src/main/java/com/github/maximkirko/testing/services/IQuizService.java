package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public interface IQuizService extends IGenericService<Quiz, Long> {

	Quiz getWithSubject(Long id);

	List<Quiz> getBySubject(Subject subject);
}
