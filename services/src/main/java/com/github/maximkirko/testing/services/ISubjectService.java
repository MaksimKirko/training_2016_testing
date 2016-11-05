package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Subject;

public interface ISubjectService {
	
	Subject get(Long id);

	List getAll();

	Long save(Subject subject);

	void saveAll(List<Subject> subjects);

	void delete(Long id);
}
