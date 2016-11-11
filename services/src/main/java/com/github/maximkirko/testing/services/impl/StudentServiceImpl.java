package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximkirko.testing.daodb.IStudentDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.users.Student;
import com.github.maximkirko.testing.services.IGradeService;
import com.github.maximkirko.testing.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Inject
	private IStudentDao studentDao;

	@Inject
	private IGradeService gradeService;

	@Override
	public Student get(Long id) {

		Student student = (Student) studentDao.get(id);
		student.setGrades(gradeService.getByStudentId(id));

		return student;
	}

	@Transactional
	@Override
	public List getAll() {
		return studentDao.getAll();
	}

	@Transactional
	@Override
	public Long save(Student student) {

		if (student.getGrades() != null) {
			for (Grade grade : student.getGrades()) {
				gradeService.save(grade);
			}
		}

		if (student.getId() == null) {

			Long id = studentDao.insert(student);

			return id;
		} else {
			studentDao.update(student);
		}
		return student.getId();
	}

	@Transactional
	@Override
	public void saveAll(List<Student> students) {
		for (Student student : students) {
			save(student);
		}

	}

	@Transactional
	@Override
	public void delete(Long id) {
		for (Grade grade : get(id).getGrades()) {
			gradeService.delete(grade.getId());
		}

		studentDao.delete(id);
	}

}
