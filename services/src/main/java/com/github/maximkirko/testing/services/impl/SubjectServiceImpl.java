package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.ISubjectDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.services.IQuizService;
import com.github.maximkirko.testing.services.ISubjectService;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Inject
	private ISubjectDao subjectDao;

	@Inject
	private IQuizService quizService;

	@Override
	public Subject get(Long id) {
		return subjectDao.get(id);
	}

	@Override
	public Subject getWithQuizzes(Long id) {

		Subject subject = subjectDao.get(id);
		subject.setQuizzes(quizService.getBySubject(subject));

		return subject;
	}

	@Override
	public List<Subject> getAll() {
		return subjectDao.getAll();
	}

	@Override
	public Long save(Subject subject) {

		if (subject.getId() == null) {

			Long id = subjectDao.insert(subject);

			return id;
		} else {

			subjectDao.update(subject);
		}
		return subject.getId();
	}

	@Override
	public List<Long> saveAll(List<Subject> subjects) {

		List<Long> idList = new ArrayList<Long>();

		for (Subject subject : subjects) {
			idList.add(save(subject));
		}

		return idList;
	}

	@Override
	public void delete(Long id) {

		Subject subject = subjectDao.get(id);

		if (subject.getQuizzes() != null) {
			for (Quiz quiz : subject.getQuizzes()) {

				quizService.delete(quiz.getId());
			}
		}

		subjectDao.delete(id);
	}

}
