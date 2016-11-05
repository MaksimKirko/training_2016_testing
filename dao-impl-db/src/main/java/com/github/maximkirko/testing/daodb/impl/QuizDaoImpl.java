package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizDaoImpl extends GenericDaoImpl<Quiz> implements IQuizDao {
	public QuizDaoImpl() {
		super(Quiz.class);
	}
}