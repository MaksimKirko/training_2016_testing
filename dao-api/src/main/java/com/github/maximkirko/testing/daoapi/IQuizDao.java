package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface IQuizDao extends IGenericDao<Quiz, Long> {

	Quiz getWithQuestions(Long id);

	Quiz getWithSubject(Long id);

	List<Quiz> getBySubjectId(Long id);

}
