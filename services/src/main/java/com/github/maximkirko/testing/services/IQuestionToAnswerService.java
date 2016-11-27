package com.github.maximkirko.testing.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.customentity.QuestionToAnswer;

public interface IQuestionToAnswerService {

	List<QuestionToAnswer> getByQuestion(Question question);
	
	List<QuestionToAnswer> getByAnswer(Answer answer);
	
	void save(QuestionToAnswer questionToAnswer);
	
	@Transactional
	void saveAll(List<QuestionToAnswer> questionToAnswers);

	@Transactional
	void deleteByQuestion(Question question);
	
	@Transactional
	void deleteByAnswer(Answer answer);
	
}
