package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IStudentDao;
import com.github.maximkirko.testing.datamodel.users.Student;
import com.github.maximkirko.testing.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Inject
	private IStudentDao studentDao;

	@Override
	public Student get(Long id) {
		return (Student) studentDao.get(id);
	}

	@Override
	public List getAll() {
		return studentDao.getAll();
	}

	@Override
	public Long save(Student student) {

		if (student.getId() == null) {
			Long id = studentDao.insert(student);
			return id;
		} else {
			studentDao.update(student);
		}
		return student.getId();
	}

	@Override
	public void saveAll(List<Student> students) {
		for (Student student : students) {
			save(student);
		}

	}

	@Override
	public void delete(Long id) {
		studentDao.delete(id);
	}

}
