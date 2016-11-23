package com.github.maximkirko.testing.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuizToQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

@Repository
public class QuizToQuestionDaoXmlImpl implements IQuizToQuestionDao {

	public QuizToQuestionDaoXmlImpl() {

	}

	@Override
	public List<QuizToQuestion> getByQuiz(Quiz quiz) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<QuizToQuestion> getByQuestion(Question question) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void insert(QuizToQuestion entity) {

		// TODO
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteByQuiz(Quiz quiz) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByQuestion(Question question) {
		// TODO
		throw new UnsupportedOperationException();
	}

}
