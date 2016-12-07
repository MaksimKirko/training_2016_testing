package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IQuizDao;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;
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

		Quiz quiz = get(id);

		List<QuizToQuestion> qtq = quizToQuestionService.getByQuiz(quiz);
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

	@Override
	public Long save(Quiz quiz) {

		Subject subject = quiz.getSubject();
		if(subject.getId() == null) {
			subject.setId(subjectService.save(subject));
		}
		quiz.setSubject(subject);
		
		if (quiz.getId() == null) {

			Long id = quizDao.insert(quiz);
			quiz.setId(id);

		} else {

			quizDao.update(quiz);
			quizToQuestionService.deleteByQuiz(quiz);
		}

		if (quiz.getQuestions() != null) {

			for (Question question : quiz.getQuestions()) {
				questionService.save(question);
			}

			List<QuizToQuestion> quizToQuestions = QuizToQuestion.quizToQTQList(quiz);
			quizToQuestionService.saveAll(quizToQuestions);
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

		if (quiz.equals(null)) {
			return;
		}

		quizToQuestionService.deleteByQuiz(quiz);
		quizDao.delete(id);

	}

}
