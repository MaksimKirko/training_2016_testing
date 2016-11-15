package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IQuestionToAnswerDao;
import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
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
	public List<QuestionToAnswer> getByQuestion(Long id) {
		return questionToAnswerDao.getByFirstId(id);
	}

	@Override
	public List<QuestionToAnswer> getByAnswer(Long id) {
		return questionToAnswerDao.getBySecondId(id);
	}
	
	@Override
	public void save(QuestionToAnswer questionToAnswer) {
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
	public void deleteByQuestionId(Long id) {
		
		questionToAnswerDao.deleteByFirstId(id);
		
		Question question = questionService.get(id);
		for (Answer answer : question.getAnswers()) {
			answerService.delete(answer.getId());
		}
		
	}

	@Transactional
	@Override
	public void deleteByAnswerId(Long id) {
		
		questionToAnswerDao.deleteBySecondId(id);
		
		Answer answer = answerService.get(id);
		for (Question question : answer.getQuestions()) {
			questionService.delete(question.getId());
		}
		
	}
	
}
