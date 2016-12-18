package com.github.maximkirko.testing.web.converter.modeltoentity;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.web.model.AnswerModel;

public class ModelToAnswer implements Converter<AnswerModel, Answer> {

	@Override
	public Answer convert(AnswerModel model) {

		Answer answer = new Answer();
		answer.setId(model.getId());
		answer.setText(model.getText());
		answer.setCorrectness(model.isCorrectness());
		Question question = new Question();
		question.setId(model.getQuestionId());
		answer.setQuestion(question);
		return answer;
	}

}
