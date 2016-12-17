package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.HashMap;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public class QuizToMap implements IEntityToMap<Quiz> {

	@Override
	public Map<String, Object> convert(Quiz entity) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", entity.getTitle());
		params.put("description", entity.getDescription());
		params.put("subject_id", entity.getSubject().getId());
		params.put("id", entity.getId());

		return params;
	}
}
