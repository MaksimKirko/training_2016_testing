package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.ISubjectDao;
import com.github.maximkirko.testing.daodb.entitytomap.SubjectToMap;
import com.github.maximkirko.testing.daodb.mapper.SubjectMapper;
import com.github.maximkirko.testing.datamodel.models.Subject;

@Repository
public class SubjectDaoDbImpl extends GenericDaoDbImpl<Subject, Long> implements ISubjectDao {

	public SubjectDaoDbImpl() {
		super(Subject.class);
		super.entityToMap = new SubjectToMap();
		super.mapper = new SubjectMapper();
	}

	@Override
	public Subject getByTitle(String title) {
		
		return jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE title = ?", super.tableName),
				new Object[] { title }, super.mapper);
	}

}