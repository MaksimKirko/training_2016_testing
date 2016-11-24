package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daoapi.IQuestionToAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuestionToAnswerService;

@Service
public class QuestionToAnswerServiceImpl implements IQuestionToAnswerService {

	@Inject
	private IQuestionToAnswerDao questionToAnswerDao;

	@Inject
	private IQuestionService questionService;

	@Inject
	private IAnswerService answerService;

	@Override
	public List<QuestionToAnswer> getByQuestion(Question question) {
		return questionToAnswerDao.getByQuestion(question);
	}

	@Override
	public List<QuestionToAnswer> getByAnswer(Answer answer) {
		return questionToAnswerDao.getByAnswer(answer);
	}

	@Override
	public void save(QuestionToAnswer questionToAnswer) {
		
		if(questionToAnswer.getAnswer().getId().equals(null)) {
			
		}
		
		questionToAnswerDao.insert(questionToAnswer);
	}

	@Transactional
	@Override
	public void saveAll(List<QuestionToAnswer> questionToAnswers) {

		for (QuestionToAnswer questionToAnswer : questionToAnswers) {
			save(questionToAnswer);
		}

	}

	@Transactional
	@Override
	public void deleteByQuestion(Question question) {

		questionToAnswerDao.deleteByQuestion(question);

		if (!question.getAnswers().equals(null)) {
			for (Answer answer : question.getAnswers()) {
				answerService.delete(answer.getId());
			}
		}

	}

	@Transactional
	@Override
	public void deleteByAnswer(Answer answer) {

		questionToAnswerDao.deleteByAnswer(answer);

		if (answer.getQuestions() != null) {
			for (Question question : answer.getQuestions()) {
				questionService.delete(question.getId());
			}
		}

	}

}
