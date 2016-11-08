package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.daodb.customentity.CustomEntity;
import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuizService;
import com.github.maximkirko.testing.services.IQuizToQuestionService;
import com.github.maximkirko.testing.services.ISubjectService;

@Service
public class QuizServiceImpl implements IQuizService {

	@Inject
	private IQuizDao quizDao;

	@Inject
	private ISubjectService subjectService;
	
	@Inject
	private IQuestionService questionService;

	@Inject
	private IQuizToQuestionService quizToQuestionService;

	@Override
	public Quiz get(Long id) {

		Quiz quiz = (Quiz) quizDao.get(id);
		
		quiz.setSubject(subjectService.get(quiz.getSubject().getId()));
		
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
	public List getAll() {
		return quizDao.getAll();
	}

	@Transactional
	@Override
	public Long save(Quiz quiz) {
		if (quiz.getId() == null) {
			Long id = quizDao.insert(quiz);
			quiz.setId(id);

			List<QuizToQuestion> quizToQuestions = CustomEntity.quizToQTQList(quiz);
			quizToQuestionService.saveAll(quizToQuestions);

			return id;
		} else {
			quizDao.update(quiz);

			List<QuizToQuestion> quizToQuestions = CustomEntity.quizToQTQList(quiz);
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

		quizToQuestionService.deleteByQuizId(id);
		quizDao.delete(id);
	}

}
