package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.ISubjectDao;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.services.ISubjectService;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Inject
	private ISubjectDao subjectDao;

	@Override
	public Subject get(Long id) {
		return (Subject) subjectDao.get(id);
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
	public void saveAll(List<Subject> subjects) {
		for (Subject subject : subjects) {
			save(subject);
		}

	}

	@Override
	public void delete(Long id) {
		subjectDao.delete(id);
	}

}
