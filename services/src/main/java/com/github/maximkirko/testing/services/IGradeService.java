package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.User;

public interface IGradeService extends IGenericService<Grade, Long> {
	
	Grade getWithUserAndQuiz(Long id);
	
	List<Grade> getByUser(User user);
	
	List<Grade> getByQuiz(Quiz quiz);
	
}
