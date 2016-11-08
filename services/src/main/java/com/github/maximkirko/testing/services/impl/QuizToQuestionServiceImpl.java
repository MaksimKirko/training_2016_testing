package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IQuizToQuestionDao;
import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.services.IQuizToQuestionService;

@Service
public class QuizToQuestionServiceImpl implements IQuizToQuestionService {

	@Inject
	private IQuizToQuestionDao quizToQuestionDao;

	@Override
	public List<QuizToQuestion> getByQuiz(Long id) {
		return quizToQuestionDao.getByFirstId(id);
	}

	@Override
	public List<QuizToQuestion> getByQuestion(Long id) {
		return quizToQuestionDao.getBySecondId(id);
	}

	@Transactional
	@Override
	public void save(QuizToQuestion quizToQuestion) {
		quizToQuestionDao.insert(quizToQuestion);
	}

	@Transactional
	@Override
	public void saveAll(List<QuizToQuestion> quizToQuestions) {
		for (QuizToQuestion quizToQuestion : quizToQuestions) {
			save(quizToQuestion);
		}
	}

	@Transactional
	@Override
	public void deleteByQuizId(Long id) {
		quizToQuestionDao.deleteByFirstId(id);
	}

	@Transactional
	@Override
	public void deleteByQuestionId(Long id) {
		quizToQuestionDao.deleteBySecondId(id);
	}

}
