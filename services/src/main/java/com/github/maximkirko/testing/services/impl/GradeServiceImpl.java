package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daoapi.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.services.IGradeService;

@Service
public class GradeServiceImpl implements IGradeService {

	@Inject
	private IGradeDao gradeDao;

	@Override
	public Grade get(Long id) {

		return gradeDao.get(id);
	}

	@Override
	public Grade getWithUserAndQuiz(Long id) {

		return gradeDao.getWithUserAndQuiz(id);
	}

	@Override
	public List<Grade> getByUser(User user) {

		return gradeDao.getByUser(user);
	}

	@Override
	public List<Grade> getByQuiz(Quiz quiz) {

		return gradeDao.getByQuiz(quiz);
	}

	@Transactional
	@Override
	public List<Grade> getAll() {

		return gradeDao.getAll();
	}

	@Transactional
	@Override
	public Long save(Grade grade) {

		if (grade.getId() == null) {

			Long id = gradeDao.insert(grade);
			grade.setId(id);
		} else {

			gradeDao.update(grade);
		}

		return grade.getId();
	}

	@Transactional
	@Override
	public List<Long> saveAll(List<Grade> grades) {

		List<Long> idList = new ArrayList<Long>();

		for (Grade grade : grades) {
			Long id = save(grade);
			grade.setId(id);
			idList.add(id);
		}

		return idList;
	}

	@Transactional
	@Override
	public void delete(Long id) {

		gradeDao.delete(id);

	}

}
