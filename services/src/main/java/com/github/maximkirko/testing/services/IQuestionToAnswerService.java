package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;

public interface IQuestionToAnswerService {

	List<QuestionToAnswer> getByQuestion(Long id);
	
	List<QuestionToAnswer> getByAnswer(Long id);
	
	void save(QuestionToAnswer questionToAnswer);
	
	void saveAll(List<QuestionToAnswer> questionToAnswers);

	void deleteByQuestionId(Long id);
	
	void deleteByAnswerId(Long id);
	
}
