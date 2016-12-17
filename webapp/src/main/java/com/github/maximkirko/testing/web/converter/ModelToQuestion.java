package com.github.maximkirko.testing.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.web.model.QuestionModel;

public class ModelToQuestion implements Converter<QuestionModel, Question> {

	@Override
	public Question convert(QuestionModel model) {

		Question question = new Question();
		question.setId(model.getId());
		question.setText(model.getText());
		question.setHint(model.getHint());
		question.setAnswers(model.getAnswers());
		
		return question;
	}

}
