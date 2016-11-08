package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;

@Repository
public class GradeDaoImpl extends GenericDaoImpl<Grade, Long> implements IGradeDao {
	public GradeDaoImpl() {
		super(Grade.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map entityToMap(Grade entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mark", entity.getMark());
		params.put("student_id", entity.getStudent());
		params.put("quiz_id", entity.getQuiz());
		params.put("id", entity.getId());
		
		return params;
	}
}
