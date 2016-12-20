package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IQuizDao;
import com.github.maximkirko.testing.daoapi.IQuizToQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;
import com.github.maximkirko.testing.services.IGradeService;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuizService;

@Service
public class QuizServiceImpl implements IQuizService {

	@Inject
	private IQuizDao quizDao;

	@Inject
	private IQuestionService questionService;

	@Inject
	private IQuizToQuestionDao quizToQuestionDao;

	@Inject
	private IGradeService gradeService;
	
	@Override
	public Quiz get(Long id) {
		return quizDao.get(id);
	}

	@Override
	public Quiz getWithSubject(Long id) {
		return quizDao.getWithSubject(id);
	}

	@Override
	public List<Quiz> getBySubjectId(Long id) {
		return quizDao.getBySubjectId(id);
	}

	@Override
	public Quiz getWithQuestions(Long id) {

		return quizDao.getWithQuestions(id);
	}

	@Override
	public List<Quiz> getAll() {
		return quizDao.getAll();
	}

	@Override
	public Long save(Quiz quiz) {

		if (quiz == null) {
			return null;
		}

		if (quiz.getId() == null) {
			Long id = quizDao.insert(quiz);
			quiz.setId(id);
		} else {
			quizToQuestionDao.deleteByQuizId(quiz.getId());
			quizDao.update(quiz);
		}

		List<Question> questions = quiz.getQuestions();
		if (questions != null) {
			for (Question question : questions) {
				Long id = questionService.save(question);
				question.setId(id);
				quizToQuestionDao.insert(new QuizToQuestion(quiz.getId(), question.getId()));
			}
			quiz.setQuestions(questions);
		}

		return quiz.getId();
	}

	@Override
	public List<Long> saveAll(List<Quiz> quizzes) {

		List<Long> idList = new ArrayList<Long>();

		for (Quiz quiz : quizzes) {
			Long id = save(quiz);
			quiz.setId(id);
			idList.add(id);
		}

		return idList;
	}

	@Override
	public void delete(Long id) {

		Quiz quiz = getWithQuestions(id);

		if (quiz == null) {
			return;
		}

		List<QuizToQuestion> q2q = quizToQuestionDao.getByQuizId(id);
		quizToQuestionDao.deleteByQuizId(id);

		List<Grade> grades = gradeService.getByQuizId(id);
		if(grades != null) {
			for (Grade grade : grades) {
				gradeService.delete(grade.getId());
			}
		}
		
		quizDao.delete(id);
		
		if (q2q != null) {
			for (QuizToQuestion quizToQuestion : q2q) {
				questionService.delete(quizToQuestion.getQuestionId());
			}
		}
	}

}
