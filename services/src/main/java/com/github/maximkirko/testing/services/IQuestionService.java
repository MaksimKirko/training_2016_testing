package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Question;

public interface IQuestionService {
	
	Question get(Long id);

	List getAll();

	Long save(Question question);

	void saveAll(List<Question> questions);

	void delete(Long id);
}
