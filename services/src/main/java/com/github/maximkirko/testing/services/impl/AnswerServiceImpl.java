package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IAnswerDao;
import com.github.maximkirko.testing.daodb.IQuestionToAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.services.IAnswerService;

@Service
public class AnswerServiceImpl implements IAnswerService {

	@Inject
	private IAnswerDao answerDao;

	@Inject
	private IQuestionToAnswerDao questionToAnswerDao;
	
	@Override
	public Answer get(Long id) {
		return (Answer) answerDao.get(id);
	}

	@Override
	public List getAll() {
		return answerDao.getAll();
	}

	@Override
	public Long save(Answer answer) {
		if (answer.getId() == null) {
			Long id = answerDao.insert(answer);
			return id;
		} else {
			answerDao.update(answer);
		}
		return answer.getId();
	}

	@Override
	public void saveAll(List<Answer> answers) {
		for (Answer answer : answers) {
			save(answer);
		}
	}

	@Override
	public void delete(Long id) {
		
		questionToAnswerDao.deleteBySecondId(id);
		answerDao.delete(id);
	}

}
