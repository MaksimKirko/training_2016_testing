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

}
