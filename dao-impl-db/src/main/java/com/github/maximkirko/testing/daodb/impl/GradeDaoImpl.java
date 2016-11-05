package com.github.maximkirko.testing.daodb.impl;

import com.github.maximkirko.testing.daodb.IGradeDao;
import com.github.maximkirko.testing.datamodel.models.Grade;

public class GradeDaoImpl extends GenericDaoImpl<Grade> implements IGradeDao {
	public GradeDaoImpl() {
		super(Grade.class);
	}

}
