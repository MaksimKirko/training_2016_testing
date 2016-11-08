package com.github.maximkirko.testing.daodb;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;

public interface IGradeDao extends IGenericDao<Grade, Long> {
	
	List<Grade> getByStudentId(Long id);
	
	List<Grade> getByQuizId(Long id);
}
