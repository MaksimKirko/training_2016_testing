package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuestionToAnswerDao;
import com.github.maximkirko.testing.daodb.mapper.QuestionToAnswerMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.customentity.QuestionToAnswer;

@Repository
public class QuestionToAnswerDaoDbImpl implements IQuestionToAnswerDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	private String tableName;

	private RowMapper<QuestionToAnswer> mapper;

	public QuestionToAnswerDaoDbImpl() {

		tableName = DBTableNameAware.getTableNameByClass(QuestionToAnswer.class);
		mapper = new QuestionToAnswerMapper();
	}

	@Override
	public List<QuestionToAnswer> getByQuestion(Question question) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE  question_id = ?", tableName),
				new Object[] { question.getId() }, mapper);
	}

	@Override
	public List<QuestionToAnswer> getByAnswer(Answer answer) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE answer_id = ?", tableName),
				new Object[] { answer.getId() }, mapper);
	}

	@Override
	public void insert(QuestionToAnswer entity) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("question_id", entity.getQuestion().getId());
		params.put("answer_id", entity.getAnswer().getId());
		
		insert.execute(params);

	}

	@Override
	public void deleteByQuestion(Question question) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE question_id = ?", tableName), question.getId());
	}

	@Override
	public void deleteByAnswer(Answer answer) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE answer_id = ?", tableName), answer.getId());
	}

}
