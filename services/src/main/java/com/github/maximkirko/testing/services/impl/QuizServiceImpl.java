package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.QuizDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Inject
	private QuizDao quizDao;

	@Override
	public Quiz get(Long id) {
		return (Quiz) quizDao.get(id);
	}

	@Override
	public List getAll() {
		return quizDao.getAll();
	}

	@Override
	public void saveAll(List<Quiz> quizzes) {
		for (Quiz quiz : quizzes) {
			save(quiz);
		}

	}

	@Override
	public void save(Quiz quiz) {

		if (quiz.getId() == null) {
			quizDao.insert(quiz);
		} else {
			quizDao.update(quiz);
		}
	}

	@Override
	public void delete(Long id) {
		quizDao.delete(id);
	}

}
