package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.services.IGradeService;
import com.github.maximkirko.testing.services.IQuizService;
import com.github.maximkirko.testing.services.IStudentService;

@Service
public class GradeServiceImpl implements IGradeService {

	@Inject
	private IGradeDao gradeDao;

	@Inject
	private IQuizService quizService;

	@Inject
	private IStudentService studentService;

	@Transactional
	@Override
	public Grade get(Long id) {

		Grade grade = (Grade) gradeDao.get(id);

		grade.setQuiz(quizService.get(grade.getQuiz().getId()));
		grade.setStudent(studentService.get(grade.getStudent().getId()));

		return grade;
	}
	
	@Override
	public List<Grade> getByStudentId(Long id) {
		return gradeDao.getByStudentId(id);
	}

	@Override
	public List<Grade> getByQuizId(Long id) {
		return gradeDao.getByQuizId(id);
	}

	@Transactional
	@Override
	public List getAll() {
		return gradeDao.getAll();
	}

	@Transactional
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

	@Transactional
	@Override
	public void saveAll(List<Grade> grades) {
		for (Grade grade : grades) {
			save(grade);
		}

	}

	@Transactional
	@Override
	public void delete(Long id) {
		gradeDao.delete(id);
	}

}
