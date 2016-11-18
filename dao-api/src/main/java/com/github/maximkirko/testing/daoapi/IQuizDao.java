package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public interface IQuizDao extends IGenericDao<Quiz, Long> {

	Quiz getWithSubject(Long id);
	
	List<Quiz> getBySubject(Subject subject);
	
}
