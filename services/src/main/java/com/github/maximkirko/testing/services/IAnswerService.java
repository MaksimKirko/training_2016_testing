package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Answer;

public interface IAnswerService {
	
	Answer get(Long id);
	
	Answer getWithQuestions(Long id);

	List getAll();

	Long save(Answer answer);

	void saveAll(List<Answer> answers);

	void delete(Long id);
}
