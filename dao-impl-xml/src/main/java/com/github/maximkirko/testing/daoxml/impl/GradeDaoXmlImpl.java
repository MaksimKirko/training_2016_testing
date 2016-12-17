package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;

@Repository
public class GradeDaoXmlImpl extends GenericDaoXmlImpl<Grade, Long> implements IGradeDao {

	public GradeDaoXmlImpl() throws IOException {
		super(Grade.class);
	}

	@Override
	public Grade get(Long id) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Grade> getByUserId(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Grade> getByQuizId(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

}
