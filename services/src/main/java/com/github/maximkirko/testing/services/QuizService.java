package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface QuizService {

	Quiz get(Long id);
	
	List getAll();

	void save(Quiz quiz);

	void saveAll(List<Quiz> quizzes);

	void delete(Long id);
}
