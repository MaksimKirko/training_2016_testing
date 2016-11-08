package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuizToQuestionDao;
import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.daodb.mapper.QuizToQuestionMapper;

@Repository
public class QuizToQuestionDaoImpl extends GenericManyToManyDaoImpl<QuizToQuestion, Long, Long>
		implements IQuizToQuestionDao {
	
	public QuizToQuestionDaoImpl() {
		super(QuizToQuestion.class, new QuizToQuestionMapper());
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Map> entityToMap(QuizToQuestion entity) {
//		List<Map> paramsList = new ArrayList<Map>();
//
//		for (Question question : entity.getQuestions()) {
//			Map<String, Long> params = new HashMap<String, Long>();
//			params.put("quiz_id", entity.getId());
//			params.put("question_id", question.getId());
//
//			paramsList.add(params);
//		}
//
//		return paramsList;
//	}
//
//	@Override
//	public List<QuizToQuestion> getByQuizId(Long id) {
//		return super.jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", super.tableName), new Object[] { id },
//				new QuizToQuestionMapper());
//	}
}
