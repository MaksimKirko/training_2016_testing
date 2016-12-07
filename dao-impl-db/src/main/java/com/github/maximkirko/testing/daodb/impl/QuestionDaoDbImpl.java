package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuestionDao;
import com.github.maximkirko.testing.daodb.entitytomap.QuestionToMap;
import com.github.maximkirko.testing.daodb.mapper.QuestionMapper;
import com.github.maximkirko.testing.datamodel.models.Question;

@Repository
public class QuestionDaoDbImpl extends GenericDaoDbImpl<Question, Long> implements IQuestionDao {
	
	public QuestionDaoDbImpl() {
		super(Question.class);
		super.entityToMap = new QuestionToMap();
		super.mapper = new QuestionMapper();
	}

}
