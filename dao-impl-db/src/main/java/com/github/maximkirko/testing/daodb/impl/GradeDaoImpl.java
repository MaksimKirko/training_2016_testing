package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGradeDao;
import com.github.maximkirko.testing.daodb.mapper.GradeMapper;
import com.github.maximkirko.testing.datamodel.models.Grade;

@Repository
public class GradeDaoImpl extends GenericDaoImpl<Grade, Long> implements IGradeDao {
	public GradeDaoImpl() {
		super(Grade.class, new GradeMapper());
	}

	@Override
	public Map entityToMap(Grade entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mark", entity.getMark());
		params.put("student_id", entity.getStudent().getId());
		params.put("quiz_id", entity.getQuiz().getId());
		params.put("id", entity.getId());
		
		return params;
	}

	@Override
	public List<Grade> getByStudentId(Long id) {
		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE student_id = ?", super.tableName),
				new Object[] { id }, mapper);
	}

	@Override
	public List<Grade> getByQuizId(Long id) {
		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", super.tableName),
				new Object[] { id }, mapper);
	}
}
