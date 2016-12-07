package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.ISubjectDao;
import com.github.maximkirko.testing.datamodel.models.Subject;

@Repository
public class SubjectDaoXmlImpl extends GenericDaoXmlImpl<Subject, Long> implements ISubjectDao {

	public SubjectDaoXmlImpl() throws IOException {
		super(Subject.class);
	}

	@Override
	public Subject getByTitle(String title) {
		// TODO
		throw new UnsupportedOperationException();
	}

}