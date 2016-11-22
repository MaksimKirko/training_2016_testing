package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.HashMap;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.Subject;

public class SubjectToMap implements EntityToMap<Subject> {
	
	@Override
	public Map<String, Object> convert(Subject entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", entity.getTitle());
		params.put("description", entity.getDescription());
		params.put("id", entity.getId());
		
		return params;
	}
}
