package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IQuestionDao;
import com.github.maximkirko.testing.daodb.customentity.CustomEntity;
import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuestionToAnswerService;
import com.github.maximkirko.testing.services.ISubjectService;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Inject
	private IQuestionDao questionDao;

	@Inject
	private IAnswerService answerService;
	
	@Inject
	private IQuestionToAnswerService questionToAnswerService;

	@Override
	public Question get(Long id) {
		
		Question question = (Question) questionDao.get(id);
		
		List<QuestionToAnswer> qta = questionToAnswerService.getByQuestion(id);
		List<Answer> answers = new ArrayList<Answer>();
		for (QuestionToAnswer questionToAnswer : qta) {
			Answer answer = answerService.get(questionToAnswer.getAnswer().getId());
			answers.add(answer);
		}
		question.setAnswers(answers);
				
		return question;
	}

	@Override
	public List getAll() {
		return questionDao.getAll();
	}

	@Transactional
	@Override
	public Long save(Question question) {
		if (question.getId() == null) {
			Long id = questionDao.insert(question);
			question.setId(id);

			List<QuestionToAnswer> questionToAnswers = CustomEntity.questionQTAList(question);
			questionToAnswerService.saveAll(questionToAnswers);

			return id;
		} else {
			questionDao.update(question);
			
			List<QuestionToAnswer> questionToAnswers = CustomEntity.questionQTAList(question);
			questionToAnswerService.saveAll(questionToAnswers);
			
		}
		return question.getId();
	}

	@Transactional
	@Override
	public void saveAll(List<Question> questions) {
		for (Question question : questions) {
			save(question);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) {

		questionToAnswerService.deleteByQuestionId(id);
		questionDao.delete(id);
	}

}
