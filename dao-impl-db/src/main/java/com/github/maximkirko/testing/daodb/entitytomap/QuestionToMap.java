package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.HashMap;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.Question;

public class QuestionToMap implements IEntityToMap<Question> {
	
	@Override
	public Map<String, Object> convert(Question entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", entity.getText());
		params.put("hint", entity.getHint());
		params.put("id", entity.getId());
		
		return params;
	}
}
