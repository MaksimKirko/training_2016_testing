package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.services.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Inject
	private IQuestionDao questionDao;

	@Override
	public Question get(Long id) {
		return (Question) questionDao.get(id);
	}

	@Override
	public List getAll() {
		return questionDao.getAll();
	}

	@Override
	public Long save(Question question) {
		if (question.getId() == null) {
			Long id = questionDao.insert(question);
			return id;
		} else {
			questionDao.update(question);
		}
		return question.getId();
	}

	@Override
	public void saveAll(List<Question> questions) {
		for (Question question : questions) {
			save(question);
		}
	}

	@Override
	public void delete(Long id) {
		questionDao.delete(id);
	}

}
