package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IStudentDao;
import com.github.maximkirko.testing.datamodel.users.Student;

@Repository
public class StudentDaoImpl extends GenericDaoImpl<Student, Long> implements IStudentDao {
	public StudentDaoImpl() {
		super(Student.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map entityToMap(Student entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("first_name", entity.getFirstName());
		params.put("last_name", entity.getLastName());
		params.put("age", entity.getAge());
		params.put("course", entity.getCourse());
		params.put("email", entity.getEmail());
		params.put("password", entity.getPassword());
		params.put("id", entity.getId());
		
		return params;
	}
}
