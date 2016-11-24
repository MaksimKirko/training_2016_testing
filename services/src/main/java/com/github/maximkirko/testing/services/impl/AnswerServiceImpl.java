package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.services.IQuestionToAnswerService;

@Service
public class AnswerServiceImpl implements IAnswerService {

	@Inject
	private IAnswerDao answerDao;

	@Inject
	private IQuestionService questionService;

	@Inject
	private IQuestionToAnswerService questionToAnswerService;

	@Override
	public Answer get(Long id) {

		return answerDao.get(id);
	}

	@Override
	public Answer getWithQuestions(Long id) {

		Answer answer = get(id);

		List<QuestionToAnswer> qta = questionToAnswerService.getByAnswer(answer);
		List<Question> questions = new ArrayList<Question>();

		for (QuestionToAnswer questionToAnswer : qta) {

			Question question = questionService.get(questionToAnswer.getQuestion().getId());
			questions.add(question);

		}
		answer.setQuestions(questions);

		return answer;
	}

	@Override
	public List<Answer> getAll() {
		return answerDao.getAll();
	}

	@Override
	public Long save(Answer answer) {

		if (answer.getId() == null) {

			Long id = answerDao.insert(answer);
			answer.setId(id);

		} else {

			answerDao.update(answer);
			questionToAnswerService.deleteByAnswer(answer);
		}

		if (answer.getQuestions() != null) {
			
			for (Question question : answer.getQuestions()) {
				questionService.save(question);
			}
			
			List<QuestionToAnswer> questionToAnswers = QuestionToAnswer.answerQTAList(answer);
			questionToAnswerService.saveAll(questionToAnswers);
		}

		return answer.getId();
	}

	@Override
	public List<Long> saveAll(List<Answer> answers) {

		List<Long> idList = new ArrayList<Long>();
		
		for (Answer answer : answers) {
			idList.add(save(answer));
		}
		
		return idList;
	}

	@Override
	public void delete(Long id) {

		Answer answer = get(id);
		
		if (answer.equals(null)) {
			return;
		}

		questionToAnswerService.deleteByAnswer(answer);
		answerDao.delete(id);
	}

}
