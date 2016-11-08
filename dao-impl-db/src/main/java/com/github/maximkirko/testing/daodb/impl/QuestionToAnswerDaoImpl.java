package com.github.maximkirko.testing.daodb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.maximkirko.testing.daodb.IQuestionToAnswerDao;
import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.daodb.mapper.QuestionToAnswerMapper;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

public class QuestionToAnswerDaoImpl extends GenericManyToManyDaoImpl<Question, Answer, Long, Long>
		implements IQuestionToAnswerDao {

	public QuestionToAnswerDaoImpl() {
		super(Question.class, Answer.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Map> entityToMap(Question entity1) {
		List<Map> paramsList = new ArrayList<Map>();

		for (Answer answer : entity1.getAnswers()) {
			Map<String, Long> params = new HashMap<String, Long>();
			params.put("question_id", entity1.getId());
			params.put("answer_id", answer.getId());

			paramsList.add(params);
		}

		return paramsList;
	}

	@Override
	public List<QuestionToAnswer> getByQuestionId(Long id) {
		return super.jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", super.tableName),
				new Object[] { id }, new QuestionToAnswerMapper());
	}

}
