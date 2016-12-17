package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
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
	public List<Grade> getByUserId(Long id) {

		return gradeDao.getByUserId(id);
	}

	@Override
	public List<Grade> getByQuizId(Long id) {

		return gradeDao.getByQuizId(id);
	}

	@Override
	public List<Grade> getAll() {

		return gradeDao.getAll();
	}

	@Override
	public Long save(Grade grade) {

		if (grade == null) {
			return null;
		}

		if (grade.getId() == null) {
			Long id = gradeDao.insert(grade);
			grade.setId(id);
		} else {
			gradeDao.update(grade);
		}
		return grade.getId();
	}

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

	@Override
	public void delete(Long id) {
		gradeDao.delete(id);
	}

}
