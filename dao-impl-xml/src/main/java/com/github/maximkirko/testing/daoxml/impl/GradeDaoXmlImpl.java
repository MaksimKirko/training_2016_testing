package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Student;

@Repository
public class GradeDaoXmlImpl extends GenericDaoXmlImpl<Grade, Long> implements IGradeDao {

	public GradeDaoXmlImpl() throws IOException {

		super(Grade.class);
	}

	@Override
	public Grade getWithStudentAndQuiz(Long id) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Grade> getByStudent(Student student) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Grade> getByQuiz(Quiz quiz) {
		// TODO
		throw new UnsupportedOperationException();
	}

}
