package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.daodb.mapper.QuizMapper;
import com.github.maximkirko.testing.daodb.mapper.QuizWithSubjectMapper;
import com.github.maximkirko.testing.daodb.util.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

@Repository
public class QuizDaoImpl extends GenericDaoImpl<Quiz, Long> implements IQuizDao {

	private String subjectTableName;

	public QuizDaoImpl() {
		super(Quiz.class, new QuizMapper());
		subjectTableName = DBTableNameAware.getTableNameByClass(Subject.class);
	}

	@Override
	public Map<String, Object> entityToMap(Quiz entity) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", entity.getTitle());
		params.put("description", entity.getDescription());
		params.put("subject_id", entity.getSubject().getId());
		params.put("id", entity.getId());

		return params;
	}

	@Override
	public Quiz getWithSubject(Long id) {

		return jdbcTemplate
				.queryForObject(String.format("SELECT * FROM %s q LEFT JOIN %s s ON q.subject_id=s.id WHERE q.id = ?",
						super.tableName, subjectTableName), new Object[] { id }, new QuizWithSubjectMapper());
	}

	@Override
	public List<Quiz> getBySubject(Subject subject) {
		
		return jdbcTemplate
				.query(String.format("SELECT * FROM %s WHERE subject_id = ?",
						super.tableName), new Object[] { subject.getId() }, super.mapper);
	}
}