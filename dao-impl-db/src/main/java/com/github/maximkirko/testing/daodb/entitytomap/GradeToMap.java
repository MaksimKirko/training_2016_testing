package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.HashMap;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.Grade;

public class GradeToMap implements EntityToMap<Grade> {
	
	@Override
	public Map<String, Object> convert(Grade entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mark", entity.getMark());
		params.put("student_id", entity.getStudent().getId());
		params.put("quiz_id", entity.getQuiz().getId());
		params.put("id", entity.getId());

		return params;
	}
}
