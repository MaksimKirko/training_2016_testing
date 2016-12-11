package com.github.maximkirko.testing.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.web.model.QuestionModel;

public class QuestionToModel implements Converter<Question, QuestionModel> {

	@Override
	public QuestionModel convert(Question question) {

		QuestionModel model = new QuestionModel();
		model.setId(question.getId());
		model.setText(question.getText());
		model.setHint(question.getHint());
		model.setAnswers(question.getAnswers());

		return model;
	}
}
