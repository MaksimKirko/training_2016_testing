package com.github.maximkirko.testing.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

@Transactional
public interface IQuizToQuestionService {

	List<QuizToQuestion> getByQuizId(Long id);

	List<QuizToQuestion> getByQuestionId(Long id);

	void insert(QuizToQuestion entity);

	void deleteByQuizId(Long id);

	void deleteByQuestionId(Long id);

}
