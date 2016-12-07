package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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
		questionToAnswerDao.insert(questionToAnswer);
	}

	@Override
	public void saveAll(List<QuestionToAnswer> questionToAnswers) {

		for (QuestionToAnswer questionToAnswer : questionToAnswers) {
			save(questionToAnswer);
		}
	}

	@Override
	public void deleteByQuestion(Question question) {

		questionToAnswerDao.deleteByQuestion(question);

		if (question.getAnswers() != null) {
			for (Answer answer : question.getAnswers()) {
				answerService.delete(answer.getId());
			}
		}
	}

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
