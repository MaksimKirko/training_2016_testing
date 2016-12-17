package com.github.maximkirko.testing.services;

import com.github.maximkirko.testing.datamodel.models.Question;

public interface IQuestionService extends IGenericService<Question, Long> {
	
	Question getWithAnswers(Long id);
	
}
