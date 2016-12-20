package com.github.maximkirko.testing.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuizToQuestionDao;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

@Repository
public class QuizToQuestionDaoXmlImpl implements IQuizToQuestionDao {

	public QuizToQuestionDaoXmlImpl() {

	}

	@Override
	public List<QuizToQuestion> getByQuizId(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<QuizToQuestion> getByQuestionId(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void insert(QuizToQuestion entity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByQuizId(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByQuestionId(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

}
