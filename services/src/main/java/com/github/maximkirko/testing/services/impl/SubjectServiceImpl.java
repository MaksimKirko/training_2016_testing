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
	public Subject getByTitle(String title) {
		return subjectDao.getByTitle(title);
	}
	
	@Override
	public Subject getWithQuizzes(Long id) {

		return subjectDao.getWithQuizzes(id);
	}

	@Override
	public List<Subject> getAll() {
		return subjectDao.getAll();
	}

	@Override
	public Long save(Subject subject) {

		if(subject == null) {
			return null;
		}
		
		if (subject.getId() == null) {

			Long id = subjectDao.insert(subject);
			subject.setId(id);
		} else {

			subjectDao.update(subject);
		}

		return subject.getId();
	}

	@Override
	public List<Long> saveAll(List<Subject> subjects) {

		List<Long> idList = new ArrayList<Long>();

		for (Subject subject : subjects) {
			Long id = save(subject);
			subject.setId(id);
			idList.add(id);
		}

		return idList;
	}

	@Override
	public void delete(Long id) {

		List<Quiz> quizzes = quizService.getBySubjectId(id);
		if (quizzes != null) {
			for (Quiz quiz : quizzes) {

				quizService.delete(quiz.getId());
			}
		}

		subjectDao.delete(id);
	}

}
