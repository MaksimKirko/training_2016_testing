package com.github.maximkirko.testing.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IQuizToQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.services.IQuizToQuestionService;

@Service
public class QuizToQuestionServiceImpl implements IQuizToQuestionService {

	@Inject
	private IQuizToQuestionDao quizToQuestionDao;
	
	@Override
	public void save(Quiz quiz) {
		quizToQuestionDao.insert(quiz);
	}

	@Override
	public void deleteByQuizId(Long id) {
		quizToQuestionDao.deleteByFirstId(id);		
	}

	@Override
	public void deleteByQuestionId(Long id) {
		quizToQuestionDao.deleteBySecondId(id);
	}

}
