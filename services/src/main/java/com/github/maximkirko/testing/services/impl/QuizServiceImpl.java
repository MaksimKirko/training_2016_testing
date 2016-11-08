package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IQuestionDao;
import com.github.maximkirko.testing.daodb.IQuizDao;
import com.github.maximkirko.testing.daodb.IQuizToQuestionDao;
import com.github.maximkirko.testing.daodb.ISubjectDao;
import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.services.IQuizService;

@Service
public class QuizServiceImpl implements IQuizService {

	@Inject
	private IQuizDao quizDao;
	
	@Inject
	private ISubjectDao subjectDao;
	
	@Inject
	private IQuestionDao questionDao;
	
	@Inject
	private IQuizToQuestionDao quizToQuestionDao;

	@Override
	public Quiz get(Long id) {
		
		Quiz quiz = (Quiz) quizDao.get(id);
		
		List<QuizToQuestion> qtq = quizToQuestionDao.getByQuizId(id);
		
		List<Question> questions = quizToQuestionDemapper(qtq);
		quiz.setQuestions(questions);
		
		return quiz;
	}
	
	public List<Question> quizToQuestionDemapper(List<QuizToQuestion> qtq) {
		List<Question> questions = new ArrayList<Question>();
		for (QuizToQuestion quizToQuestion : qtq) {
			
			Question question = questionDao.get(quizToQuestion.getQuestion().getId());
			questions.add(question);
		}

		return questions;
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
		
		quizToQuestionDao.deleteByFirstId(id);
		quizDao.delete(id);
	}

}
