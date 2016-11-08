package com.github.maximkirko.testing.daodb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuizToQuestionDao;
import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.daodb.mapper.QuizToQuestionMapper;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizToQuestionDaoImpl extends GenericManyToManyDaoImpl<Quiz, Question, Long, Long>
		implements IQuizToQuestionDao {
	
	public QuizToQuestionDaoImpl() {
		super(Quiz.class, Question.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Map> entityToMap(Quiz entity1) {
		List<Map> paramsList = new ArrayList<Map>();

		for (Question question : entity1.getQuestions()) {
			Map<String, Long> params = new HashMap<String, Long>();
			params.put("quiz_id", entity1.getId());
			params.put("question_id", question.getId());

			paramsList.add(params);
		}

		return paramsList;
	}

	@Override
	public List<QuizToQuestion> getByQuizId(Long id) {
		return super.jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", super.tableName), new Object[] { id },
				new QuizToQuestionMapper());
	}
}
