package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Answer;

@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Answer> implements IAnswerDao {
	public AnswerDaoImpl() {
		super(Answer.class);
	}
}
