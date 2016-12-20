package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuizToQuestionDao;
import com.github.maximkirko.testing.daodb.mapper.QuizToQuestionMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

@Repository
public class QuizToQuestionDaoDbImpl implements IQuizToQuestionDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	private String tableName;

	private RowMapper<QuizToQuestion> mapper;

	public QuizToQuestionDaoDbImpl() {

		tableName = DBTableNameAware.getTableNameByClass(QuizToQuestion.class);
		mapper = new QuizToQuestionMapper();
	}

	@Override
	public List<QuizToQuestion> getByQuizId(Long id) {

		List<QuizToQuestion> q2qList;
		try {
			q2qList = jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", tableName),
					new Object[] { id }, mapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return q2qList;
	}

	@Override
	public List<QuizToQuestion> getByQuestionId(Long id) {

		List<QuizToQuestion> q2qList;
		try {
			q2qList = jdbcTemplate.query(String.format("SELECT * FROM %s WHERE  question_id = ?", tableName),
					new Object[] { id }, mapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return q2qList;
	}

	@Override
	public void insert(QuizToQuestion entity) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quiz_id", entity.getQuizId());
		params.put("question_id", entity.getQuestionId());

		insert.execute(params);

	}

	@Override
	public void deleteByQuizId(Long id) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE quiz_id = ?", tableName), id);
	}

	@Override
	public void deleteByQuestionId(Long id) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE question_id = ?", tableName), id);
	}

}
