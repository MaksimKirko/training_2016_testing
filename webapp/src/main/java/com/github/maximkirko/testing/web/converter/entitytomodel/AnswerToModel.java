package com.github.maximkirko.testing.web.converter.entitytomodel;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.web.model.AnswerModel;

public class AnswerToModel implements Converter<Answer, AnswerModel> {

	@Override
	public AnswerModel convert(Answer answer) {

		AnswerModel model = new AnswerModel();
		model.setId(answer.getId());
		model.setText(answer.getText());
		model.setCorrectness(answer.isCorrectness());
		model.setQuestionId(answer.getQuestion().getId());
		return model;
	}
}
