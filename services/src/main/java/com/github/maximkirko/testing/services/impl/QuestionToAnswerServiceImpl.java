package com.github.maximkirko.testing.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IQuestionToAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.services.IQuestionToAnswerService;

@Service
public class QuestionToAnswerServiceImpl implements IQuestionToAnswerService {

	@Inject
	private IQuestionToAnswerDao questionToAnswerDao;

	@Override
	public void save(Question question) {
		questionToAnswerDao.insert(question);
	}

	@Override
	public void deleteByQuizId(Long id) {
		questionToAnswerDao.deleteByFirstId(id);
	}

	@Override
	public void deleteByQuestionId(Long id) {
		questionToAnswerDao.deleteBySecondId(id);
	}
}
