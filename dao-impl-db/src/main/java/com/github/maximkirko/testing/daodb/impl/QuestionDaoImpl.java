package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Question;

@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Question> implements IQuestionDao {
	public QuestionDaoImpl() {
		super(Question.class);
	}
}
