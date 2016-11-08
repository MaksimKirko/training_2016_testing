package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IStudentDetailsDao;
import com.github.maximkirko.testing.datamodel.users.StudentDetails;
import com.github.maximkirko.testing.services.IStudentDetailsService;

@Service
public class StudentDetailsServiceImpl implements IStudentDetailsService {

	@Inject
	private IStudentDetailsDao studentDetailsDao;

	@Override
	public StudentDetails get(Long id) {
		return (StudentDetails) studentDetailsDao.get(id);
	}

	@Override
	public List getAll() {
		return studentDetailsDao.getAll();
	}

	@Override
	public Long save(StudentDetails studentDetails) {

		if (studentDetails.getId() == null) {
			Long id = studentDetailsDao.insert(studentDetails);
			return id;
		} else {
			studentDetailsDao.update(studentDetails);
		}
		return studentDetails.getId();
	}

	@Override
	public void saveAll(List<StudentDetails> studentsDetails) {
		for (StudentDetails studentDetails : studentsDetails) {
			save(studentDetails);
		}

	}

	@Override
	public void delete(Long id) {
		studentDetailsDao.delete(id);
	}
}
