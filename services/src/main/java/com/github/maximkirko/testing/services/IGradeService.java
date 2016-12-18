package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;

public interface IGradeService extends IGenericService<Grade, Long> {

	Grade getWithUserAndQuiz(Long id);
	
	List<Grade> getByUserId(Long id);

	List<Grade> getByQuizId(Long id);

}
