package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IStudentDao;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Student;

@Repository
public class StudentDaoXmlImpl extends GenericDaoXmlImpl<Student, Long> implements IStudentDao {

	public StudentDaoXmlImpl() throws IOException {
		super(Student.class);
	}

	@Override
	public Student getWithRole(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Student> getByRole(Role role) {

		// TODO
		throw new UnsupportedOperationException();
	}
}
