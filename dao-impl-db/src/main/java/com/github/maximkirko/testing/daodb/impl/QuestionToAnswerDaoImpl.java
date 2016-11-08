package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IQuestionToAnswerDao;
import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.daodb.mapper.QuestionToAnswerMapper;

@Repository
public class QuestionToAnswerDaoImpl extends GenericManyToManyDaoImpl<QuestionToAnswer, Long, Long> 
		implements IQuestionToAnswerDao {

	public QuestionToAnswerDaoImpl() {
		super(QuestionToAnswer.class, new QuestionToAnswerMapper());
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Map> entityToMap(QuestionToAnswer entity) {
//		List<Map> paramsList = new ArrayList<Map>();
//
//		for (Answer answer : entity.getAnswers()) {
//			Map<String, Long> params = new HashMap<String, Long>();
//			params.put("question_id", entity.getId());
//			params.put("answer_id", answer.getId());
//
//			paramsList.add(params);
//		}
//
//		return paramsList;
//	}
//
//	@Override
//	public List<QuestionToAnswer> getByQuestionId(Long id) {
//		return super.jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", super.tableName),
//				new Object[] { id }, new QuestionToAnswerMapper());
//	}

}
