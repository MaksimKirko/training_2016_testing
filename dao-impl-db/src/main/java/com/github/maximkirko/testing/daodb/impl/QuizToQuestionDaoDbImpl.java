package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuizToQuestionDao;
import com.github.maximkirko.testing.daodb.mapper.QuizToQuestionMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
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
	public List<QuizToQuestion> getByQuiz(Quiz quiz) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", tableName),
				new Object[] { quiz.getId() }, mapper);
	}

	@Override
	public List<QuizToQuestion> getByQuestion(Question question) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE  question_id = ?", tableName),
				new Object[] { question.getId() }, mapper);
	}

	@Override
	public void insert(QuizToQuestion entity) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quiz_id", entity.getQuiz().getId());
		params.put("question_id", entity.getQuestion().getId());

		insert.execute(params);

	}

	@Override
	public void deleteByQuiz(Quiz quiz) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE quiz_id = ?", tableName), quiz.getId());
	}

	@Override
	public void deleteByQuestion(Question question) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE question_id = ?", tableName), question.getId());
	}

}
