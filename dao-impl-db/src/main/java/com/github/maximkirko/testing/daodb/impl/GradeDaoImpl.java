package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;

@Repository
public class GradeDaoImpl extends GenericDaoImpl<Grade> implements IGradeDao {
	public GradeDaoImpl() {
		super(Grade.class);
	}

}
