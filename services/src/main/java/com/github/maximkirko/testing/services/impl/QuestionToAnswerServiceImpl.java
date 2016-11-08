package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IQuestionToAnswerDao;
import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.services.IQuestionToAnswerService;

@Service
public class QuestionToAnswerServiceImpl implements IQuestionToAnswerService {

	@Inject
	private IQuestionToAnswerDao questionToAnswerDao;

	@Override
	public List<QuestionToAnswer> getByQuestion(Long id) {
		return questionToAnswerDao.getByFirstId(id);
	}

	@Override
	public List<QuestionToAnswer> getByAnswer(Long id) {
		return questionToAnswerDao.getBySecondId(id);
	}
	
	@Transactional
	@Override
	public void save(QuestionToAnswer questionToAnswer) {
		questionToAnswerDao.insert(questionToAnswer);
	}
	
	@Transactional
	@Override
	public void saveAll(List<QuestionToAnswer> questionToAnswers) {
		for (QuestionToAnswer questionToAnswer : questionToAnswers) {
			save(questionToAnswer);
		}
		
	}

	@Transactional
	@Override
	public void deleteByQuestionId(Long id) {
		questionToAnswerDao.deleteByFirstId(id);
	}

	@Transactional
	@Override
	public void deleteByAnswerId(Long id) {
		questionToAnswerDao.deleteBySecondId(id);
	}
	
}
