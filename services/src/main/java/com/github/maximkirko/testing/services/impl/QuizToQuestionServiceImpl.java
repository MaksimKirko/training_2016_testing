package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IQuizToQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuizService;
import com.github.maximkirko.testing.services.IQuizToQuestionService;

@Service
public class QuizToQuestionServiceImpl implements IQuizToQuestionService {

	@Inject
	private IQuizToQuestionDao quizToQuestionDao;

	@Inject
	private IQuizService quizService;

	@Inject
	private IQuestionService questionService;

	@Override
	public List<QuizToQuestion> getByQuiz(Quiz quiz) {
		return quizToQuestionDao.getByQuiz(quiz);
	}

	@Override
	public List<QuizToQuestion> getByQuestion(Question question) {
		return quizToQuestionDao.getByQuestion(question);
	}

	@Override
	public void save(QuizToQuestion questionToQuiz) {
		quizToQuestionDao.insert(questionToQuiz);
	}

	@Override
	public void saveAll(List<QuizToQuestion> questionToQuizs) {

		for (QuizToQuestion questionToQuiz : questionToQuizs) {
			save(questionToQuiz);
		}
	}

	@Override
	public void deleteByQuiz(Quiz quiz) {

		quizToQuestionDao.deleteByQuiz(quiz);

		if (quiz.getQuestions() != null) {
			for (Question question : quiz.getQuestions()) {
				questionService.delete(question.getId());
			}
		}
	}

	@Override
	public void deleteByQuestion(Question question) {

		quizToQuestionDao.deleteByQuestion(question);

		if (question.getQuizzes() != null) {
			for (Quiz quiz : question.getQuizzes()) {
				quizService.delete(quiz.getId());
			}
		}
	}

}
