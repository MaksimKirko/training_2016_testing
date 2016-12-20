package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuizDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizDaoXmlImpl extends GenericDaoXmlImpl<Quiz, Long> implements IQuizDao {

	public QuizDaoXmlImpl() throws IOException {
		super(Quiz.class);
	}

	@Override
	public Quiz getWithSubject(Long id) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Quiz> getBySubjectId(Long id) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Quiz getWithQuestions(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}
}