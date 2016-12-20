package com.github.maximkirko.testing.web.converter.modeltoentity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.web.model.QuestionModel;
import com.github.maximkirko.testing.web.model.QuizModel;

public class ModelToQuiz implements Converter<QuizModel, Quiz> {

	private ModelToQuestion modelToQuestion = new ModelToQuestion();
	private ModelToSubject modelToSubject = new ModelToSubject();

	@Override
	public Quiz convert(QuizModel model) {

		Quiz quiz = new Quiz();
		quiz.setId(model.getId());
		quiz.setTitle(model.getTitle());
		quiz.setDescription(model.getDescription());
		Subject subject = modelToSubject.convert(model.getSubject());
		quiz.setSubject(subject);

		List<Question> questions = new ArrayList<>();
		List<QuestionModel> questionModels = model.getQuestions();
		if (questionModels != null) {
			for (QuestionModel questionModel : questionModels) {
				questions.add(modelToQuestion.convert(questionModel));
			}
			quiz.setQuestions(questions);
		}

		return quiz;
	}

}
