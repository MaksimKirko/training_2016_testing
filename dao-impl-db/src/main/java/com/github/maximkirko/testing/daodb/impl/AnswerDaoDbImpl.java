package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IAnswerDao;
import com.github.maximkirko.testing.daodb.entitytomap.AnswerToMap;
import com.github.maximkirko.testing.daodb.mapper.AnswerMapper;
import com.github.maximkirko.testing.datamodel.models.Answer;

@Repository
public class AnswerDaoDbImpl extends GenericDaoDbImpl<Answer, Long> implements IAnswerDao {
	
	public AnswerDaoDbImpl() {
		super(Answer.class, new AnswerMapper(), new AnswerToMap());
	}

}
