package com.github.maximkirko.testing.web.converter.entitytomodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.web.model.AnswerModel;
import com.github.maximkirko.testing.web.model.QuestionModel;

public class QuestionToModel implements Converter<Question, QuestionModel> {

	private AnswerToModel answerToModel = new AnswerToModel();

	@Override
	public QuestionModel convert(Question question) {

		QuestionModel model = new QuestionModel();
		model.setId(question.getId());
		model.setText(question.getText());
		model.setHint(question.getHint());
		List<Answer> answers = question.getAnswers();
		List<AnswerModel> answerModels = new ArrayList<>();
		if (answers != null) {
			for (Answer answer : answers) {
				answerModels.add(answerToModel.convert(answer));
			}
			model.setAnswers(answerModels);
		}

		return model;
	}
}
