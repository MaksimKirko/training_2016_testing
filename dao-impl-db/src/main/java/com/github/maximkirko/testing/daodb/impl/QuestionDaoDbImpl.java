package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuestionDao;
import com.github.maximkirko.testing.daodb.mapper.QuestionMapper;
import com.github.maximkirko.testing.datamodel.models.Question;

@Repository
public class QuestionDaoDbImpl extends GenericDaoDbImpl<Question, Long> implements IQuestionDao {
	
	public QuestionDaoDbImpl() {
		super(Question.class, new QuestionMapper());
	}

	@Override
	public Map<String, Object> entityToMap(Question entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", entity.getText());
		params.put("hint", entity.getHint());
		params.put("id", entity.getId());
		
		return params;
	}

}
