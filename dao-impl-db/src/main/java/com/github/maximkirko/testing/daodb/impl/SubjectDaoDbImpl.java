package com.github.maximkirko.testing.daodb.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.ISubjectDao;
import com.github.maximkirko.testing.daodb.entitytomap.SubjectToMap;
import com.github.maximkirko.testing.daodb.mapper.SubjectMapper;
import com.github.maximkirko.testing.daodb.mapper.SubjectWithQuizzesMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

@Repository
public class SubjectDaoDbImpl extends GenericDaoDbImpl<Subject, Long> implements ISubjectDao {

	private String quizTableName;
	private SubjectWithQuizzesMapper subjectWithQuizzesMapper;

	public SubjectDaoDbImpl() {
		super();
		setTableName(DBTableNameAware.getTableNameByClass(Subject.class));
		setEntityToMap(new SubjectToMap());
		setMapper(new SubjectMapper());

		quizTableName = DBTableNameAware.getTableNameByClass(Quiz.class);
		subjectWithQuizzesMapper = new SubjectWithQuizzesMapper();
	}

	@Override
	public Subject getByTitle(String title) {

		return getJdbcTemplate().queryForObject(String.format("SELECT * FROM %s WHERE title = ?", getTableName()),
				new Object[] { title }, getMapper());
	}

	@Override
	public Subject getWithQuizzes(Long id) {

		Subject subject;

		try {
			subject = getJdbcTemplate().queryForObject(
					String.format("SELECT * FROM %s s LEFT JOIN %s q ON s.id=q.subject_id WHERE s.id = ?",
							getTableName(), quizTableName),
					new Object[] { id }, subjectWithQuizzesMapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return subject;
	}

}