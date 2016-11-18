package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.ISubjectDao;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;
import com.github.maximkirko.testing.datamodel.models.Subject;

@Repository
public class SubjectDaoDbImpl extends GenericDaoDbImpl<Subject, Long> implements ISubjectDao {
	public SubjectDaoDbImpl() {
		super(Subject.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map entityToMap(Subject entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", entity.getTitle());
		params.put("description", entity.getDescription());
		params.put("id", entity.getId());
		
		return params;
	}
}