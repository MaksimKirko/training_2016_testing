package com.github.maximkirko.testing.web.converter.entitytomodel;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.web.model.QuizModel;
import com.github.maximkirko.testing.web.model.SubjectModel;

public class QuizToModel implements Converter<Quiz, QuizModel> {

	@Override
	public QuizModel convert(Quiz quiz) {

		QuizModel model = new QuizModel();
		model.setId(quiz.getId());
		model.setTitle(quiz.getTitle());
		model.setDescription(quiz.getDescription());
		Subject subject = quiz.getSubject();
		SubjectModel subjectModel = new SubjectModel();
		subjectModel.setId(subject.getId());
		model.setSubject(subjectModel);
		return model;
	}

}
