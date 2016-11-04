package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.QuizDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizDaoImpl extends GenericDaoImpl<Quiz> implements QuizDao {
	public QuizDaoImpl() {
		super(Quiz.class);
	}
}