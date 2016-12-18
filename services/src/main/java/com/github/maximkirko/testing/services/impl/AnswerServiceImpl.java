package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.services.IQuestionService;

@Service
public class AnswerServiceImpl implements IAnswerService {

	@Inject
	private IAnswerDao answerDao;

	@Inject
	private IQuestionService questionService;

	@Override
	public Answer get(Long id) {
		return answerDao.get(id);
	}

	@Override
	public List<Answer> getByQuestionId(Long id) {
		return answerDao.getByQuestionId(id);
	}

	@Override
	public List<Answer> getAll() {
		return answerDao.getAll();
	}

	@Override
	public Long save(Answer answer) {

		if (answer == null) {
			return null;
		}

		if (answer.getId() == null) {
			Long id = answerDao.insert(answer);
			answer.setId(id);
		} else {
			answerDao.update(answer);
		}

		return answer.getId();
	}

	@Override
	public List<Long> saveAll(List<Answer> answers) {

		List<Long> idList = new ArrayList<Long>();

		for (Answer answer : answers) {
			Long id = save(answer);
			answer.setId(id);
			idList.add(id);
		}

		return idList;
	}

	@Override
	public void delete(Long id) {

		if (id != null) {
			answerDao.delete(id);
		}
	}

}
