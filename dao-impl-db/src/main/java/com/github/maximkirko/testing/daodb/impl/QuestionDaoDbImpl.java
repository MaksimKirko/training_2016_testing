package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuestionDao;
import com.github.maximkirko.testing.daodb.entitytomap.QuestionToMap;
import com.github.maximkirko.testing.daodb.mapper.QuestionMapper;
import com.github.maximkirko.testing.daodb.mapper.QuestionWithAnswersMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

@Repository
public class QuestionDaoDbImpl extends GenericDaoDbImpl<Question, Long> implements IQuestionDao {

	private String answerTableName;
	private QuestionWithAnswersMapper questionWithAnswersMapper;

	public QuestionDaoDbImpl() {
		super();
		setTableName(DBTableNameAware.getTableNameByClass(Question.class));
		setEntityToMap(new QuestionToMap());
		setMapper(new QuestionMapper());

		answerTableName = DBTableNameAware.getTableNameByClass(Answer.class);
		questionWithAnswersMapper = new QuestionWithAnswersMapper();
	}

	@Override
	public Question getWithAnswers(Long id) {

		Question question;
		List<Question> questions;
		
		try {
			questions = getJdbcTemplate().query(
					String.format("SELECT * FROM %s q LEFT JOIN %s a ON q.id=a.question_id WHERE q.id = ?",
							getTableName(), answerTableName),
					new Object[] { id }, questionWithAnswersMapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		if(questions.size() < 1) {
			return null;
		}
		question = questions.get(0);
		List<Answer> answers = question.getAnswers();
		for (Question q : questions) {
			answers.addAll(q.getAnswers());
		}
		question.setAnswers(answers);
		
		return question;
	}

}
