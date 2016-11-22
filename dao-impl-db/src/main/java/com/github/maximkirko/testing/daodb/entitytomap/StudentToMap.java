package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.HashMap;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.Student;

public class StudentToMap implements EntityToMap<Student> {
	
	@Override
	public Map<String, Object> convert(Student entity) {
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
