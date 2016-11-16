package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuizService;
import com.github.maximkirko.testing.services.IQuizToQuestionService;

@Service
public class QuizServiceImpl implements IQuizService {

	@Inject
	private IQuizDao quizDao;

	@Inject
	private IQuestionService questionService;

	@Inject
	private IQuizToQuestionService quizToQuestionService;

	@Override
	public Quiz get(Long id) {
		return quizDao.get(id);
	}

	@Override
	public Quiz getWithSubject(Long id) {
		return quizDao.getWithSubject(id);
	}

	@Override
	public List<Quiz> getBySubject(Subject subject) {
		return quizDao.getBySubject(subject);
	}

	@Override
	public Quiz getWithQuestions(Long id) {

		Quiz quiz = quizDao.get(id);

		List<QuizToQuestion> qtq = quizToQuestionService.getByQuiz(id);
		List<Question> questions = new ArrayList<Question>();

		for (QuizToQuestion quizToQuestion : qtq) {

			Question question = questionService.get(quizToQuestion.getQuestion().getId());
			questions.add(question);
		}
		quiz.setQuestions(questions);

		return quiz;

	}

	@Override
	public List<Quiz> getAll() {
		return quizDao.getAll();
	}

	@Transactional
	@Override
	public Long save(Quiz quiz) {

		if (quiz.getId() == null) {

			Long id = quizDao.insert(quiz);
			quiz.setId(id);

		} else {

			quizDao.update(quiz);

		}

		if (quiz.getQuestions() != null) {
			List<QuizToQuestion> quizToQuestions = QuizToQuestion.quizToQTQList(quiz);
			quizToQuestionService.saveAll(quizToQuestions);
		}

		return quiz.getId();
	}

	@Transactional
	@Override
	public void saveAll(List<Quiz> quizzes) {

		for (Quiz quiz : quizzes) {
			save(quiz);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) {

		Quiz quiz = get(id);
		for (Question question : quiz.getQuestions()) {
			questionService.delete(question.getId());
		}

		quizToQuestionService.deleteByQuizId(id);
		quizDao.delete(id);
	}

}
