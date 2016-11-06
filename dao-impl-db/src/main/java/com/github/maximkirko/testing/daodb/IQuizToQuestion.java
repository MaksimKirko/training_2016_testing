package com.github.maximkirko.testing.daodb;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface IQuizToQuestion {
	
	void insert(Quiz quiz);
	
	void deleteByQuizId(Long id);
	
	
}
