package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IQuizToQuestionDao;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;
import com.github.maximkirko.testing.services.IQuizToQuestionService;

@Service
public class QuizToQuestionServiceImpl implements IQuizToQuestionService {

	@Inject
	private IQuizToQuestionDao quizToQuestionDao;

	@Override
	public List<QuizToQuestion> getByQuizId(Long id) {
		return quizToQuestionDao.getByQuizId(id);
	}

	@Override
	public List<QuizToQuestion> getByQuestionId(Long id) {
		return quizToQuestionDao.getByQuestionId(id);
	}

	@Override
	public void insert(QuizToQuestion entity) {
		if (entity != null) {
			quizToQuestionDao.insert(entity);
		}
	}

	@Override
	public void deleteByQuizId(Long id) {
		if (id != null) {
			quizToQuestionDao.deleteByQuizId(id);
		}
	}

	@Override
	public void deleteByQuestionId(Long id) {
		if (id != null) {
			quizToQuestionDao.deleteByQuestionId(id);
		}
	}

}
