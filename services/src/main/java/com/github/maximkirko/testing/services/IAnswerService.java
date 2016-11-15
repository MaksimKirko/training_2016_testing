package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Answer;

public interface IAnswerService extends IGenericService<Answer, Long> {

	Answer getWithQuestions(Long id);
	
}
