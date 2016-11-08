package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IStudentDetailsDao;
import com.github.maximkirko.testing.datamodel.users.StudentDetails;

@Repository
public class StudentDetailsDaoImpl extends GenericDaoImpl<StudentDetails, Long> implements IStudentDetailsDao {
	public StudentDetailsDaoImpl() {
		super(StudentDetails.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map entityToMap(StudentDetails entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("age", entity.getAge());
		params.put("course", entity.getCourse());
		params.put("email", entity.getEmail());
		params.put("password", entity.getPassword());
		params.put("id", entity.getId());

		return params;
	}
}
