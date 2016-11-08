package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;

public interface IGradeService {

	Grade get(Long id);

	List<Grade> getByStudentId(Long id);

	List<Grade> getByQuizId(Long id);
	
	List getAll();

	Long save(Grade grade);

	void saveAll(List<Grade> grades);

	void delete(Long id);
}
