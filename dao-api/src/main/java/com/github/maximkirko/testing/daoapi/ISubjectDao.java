package com.github.maximkirko.testing.daoapi;

import com.github.maximkirko.testing.datamodel.models.Subject;

public interface ISubjectDao extends IGenericDao<Subject, Long> {
	
	Subject getByTitle(String title);
}
