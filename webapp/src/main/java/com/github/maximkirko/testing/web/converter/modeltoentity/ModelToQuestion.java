package com.github.maximkirko.testing.web.converter.modeltoentity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.web.model.AnswerModel;
import com.github.maximkirko.testing.web.model.QuestionModel;

public class ModelToQuestion implements Converter<QuestionModel, Question> {

	private ModelToAnswer modelToAnswer = new ModelToAnswer();

	@Override
	public Question convert(QuestionModel model) {

		Question question = new Question();
		question.setId(model.getId());
		question.setText(model.getText());
		question.setHint(model.getHint());
		List<Answer> answers = new ArrayList<>();
		List<AnswerModel> answerModels = model.getAnswers();
		if (answerModels != null) {
			for (AnswerModel answerModel : answerModels) {
				answers.add(modelToAnswer.convert(answerModel));
			}
			question.setAnswers(answers);
		}
		return question;
	}

}
