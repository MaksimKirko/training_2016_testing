package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;

public interface IGradeDao extends IGenericDao<Grade, Long> {
	
	Grade getWithUserAndQuiz(Long id);
	
	List<Grade> getByUserId(Long id);
	
	List<Grade> getByQuizId(Long id);
	
}
