package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuizDao;
import com.github.maximkirko.testing.daodb.entitytomap.QuizToMap;
import com.github.maximkirko.testing.daodb.mapper.QuizMapper;
import com.github.maximkirko.testing.daodb.mapper.QuizWithSubjectMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

@Repository
public class QuizDaoDbImpl extends GenericDaoDbImpl<Quiz, Long> implements IQuizDao {

	private String subjectTableName;

	public QuizDaoDbImpl() {
		super(Quiz.class);
		super.entityToMap = new QuizToMap();
		super.mapper = new QuizMapper();
		subjectTableName = DBTableNameAware.getTableNameByClass(Subject.class);
	}

	@Override
	public Quiz getWithSubject(Long id) {

		return jdbcTemplate
				.queryForObject(String.format("SELECT * FROM %s q LEFT JOIN %s s ON q.subject_id=s.id WHERE q.id = ?",
						super.tableName, subjectTableName), new Object[] { id }, new QuizWithSubjectMapper());
	}

	@Override
	public List<Quiz> getBySubject(Subject subject) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE subject_id = ?", super.tableName),
				new Object[] { subject.getId() }, super.mapper);
	}
}