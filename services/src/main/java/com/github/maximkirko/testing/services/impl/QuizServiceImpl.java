package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.services.IQuizService;

@Service
public class QuizServiceImpl implements IQuizService {

	@Inject
	private IQuizDao quizDao;

	@Override
	public Quiz get(Long id) {
		return (Quiz) quizDao.get(id);
	}

	@Override
	public List getAll() {
		return quizDao.getAll();
	}

	@Override
	public Long save(Quiz quiz) {

		if (quiz.getId() == null) {
			Long id = quizDao.insert(quiz);
			return id;
		} else {
			quizDao.update(quiz);
		}
		return quiz.getId();
	}

	@Override
	public void saveAll(List<Quiz> quizzes) {
		for (Quiz quiz : quizzes) {
			save(quiz);
		}

	}

	@Override
	public void delete(Long id) {
		quizDao.delete(id);
	}

}
