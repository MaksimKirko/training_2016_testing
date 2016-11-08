package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.daodb.mapper.QuizMapper;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizDaoImpl extends GenericDaoImpl<Quiz, Long> implements IQuizDao {
	public QuizDaoImpl() {
		super(Quiz.class, new QuizMapper());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map entityToMap(Quiz entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", entity.getTitle());
		params.put("description", entity.getDescription());
		params.put("subject_id", entity.getSubject().getId());
		params.put("id", entity.getId());
		
		return params;
	}
}