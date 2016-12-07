package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.User;

public interface IGradeDao extends IGenericDao<Grade, Long> {

	Grade getWithUserAndQuiz(Long id);
	
	List<Grade> getByUser(User student);
	
	List<Grade> getByQuiz(Quiz quiz);
	
}
