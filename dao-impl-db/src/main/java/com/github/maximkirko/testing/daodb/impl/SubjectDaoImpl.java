package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.ISubjectDao;
import com.github.maximkirko.testing.datamodel.models.Subject;

@Repository
public class SubjectDaoImpl extends GenericDaoImpl<Subject> implements ISubjectDao {
	public SubjectDaoImpl() {
		super(Subject.class);
	}
}