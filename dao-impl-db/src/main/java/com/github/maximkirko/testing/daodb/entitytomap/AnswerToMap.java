package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.HashMap;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.Answer;

public class AnswerToMap implements IEntityToMap<Answer> {

	@Override
	public Map<String, Object> convert(Answer entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", entity.getText());
		params.put("correctness", entity.isCorrectness());
		params.put("question_id", entity.getQuestion().getId());
		params.put("id", entity.getId());

		return params;
	}
}
