package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IAnswerDao;
import com.github.maximkirko.testing.daodb.mapper.AnswerMapper;
import com.github.maximkirko.testing.datamodel.models.Answer;

@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Answer, Long> implements IAnswerDao {
	
	public AnswerDaoImpl() {
		super(Answer.class, new AnswerMapper());
	}

	@Override
	public Map<String, Object> entityToMap(Answer entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", entity.getText());
		params.put("id", entity.getId());
		
		return params;
	}

}
