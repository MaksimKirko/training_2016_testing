package com.github.maximkirko.testing.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuestionToAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.customentity.QuestionToAnswer;

@Repository
public class QuestionToAnswerDaoXmlImpl implements IQuestionToAnswerDao {

	public QuestionToAnswerDaoXmlImpl() {

	}

	@Override
	public List<QuestionToAnswer> getByQuestion(Question question) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<QuestionToAnswer> getByAnswer(Answer answer) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void insert(QuestionToAnswer entity) {

		// TODO
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteByQuestion(Question question) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByAnswer(Answer answer) {

//		// TODO
//		throw new UnsupportedOperationException();
	}

}
