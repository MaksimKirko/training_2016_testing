package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuestionToAnswerDao;
import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.daodb.mapper.QuestionToAnswerMapper;

@Repository
public class QuestionToAnswerDaoImpl extends GenericManyToManyDaoImpl<QuestionToAnswer, Long, Long> 
		implements IQuestionToAnswerDao {

	public QuestionToAnswerDaoImpl() {
		super(QuestionToAnswer.class, new QuestionToAnswerMapper());
	}

}
