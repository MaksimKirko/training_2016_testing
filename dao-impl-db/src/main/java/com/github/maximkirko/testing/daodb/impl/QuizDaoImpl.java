package com.github.maximkirko.testing.daodb.impl;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.QuizDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizDaoImpl extends GenericDaoImpl<Quiz> implements QuizDao {
	public QuizDaoImpl() {
		super(Quiz.class);
	}
}
