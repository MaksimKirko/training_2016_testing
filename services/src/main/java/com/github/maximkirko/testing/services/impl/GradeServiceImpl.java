package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.services.IGradeService;

@Service
public class GradeServiceImpl implements IGradeService {

	@Inject
	private IGradeDao gradeDao;

	@Override
	public Grade get(Long id) {
		return (Grade) gradeDao.get(id);
	}

	@Override
	public List getAll() {
		return gradeDao.getAll();
	}

	@Override
	public Long save(Grade grade) {

		if (grade.getId() == null) {
			Long id = gradeDao.insert(grade);
			return id;
		} else {
			gradeDao.update(grade);
		}
		return grade.getId();
	}

	@Override
	public void saveAll(List<Grade> grades) {
		for (Grade grade : grades) {
			save(grade);
		}

	}

	@Override
	public void delete(Long id) {
		gradeDao.delete(id);
	}

}
