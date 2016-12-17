package com.github.maximkirko.testing.services;

import com.github.maximkirko.testing.datamodel.models.Subject;

public interface ISubjectService extends IGenericService<Subject, Long> {

	Subject getByTitle(String title);
	
	Subject getWithQuizzes(Long id);

}
