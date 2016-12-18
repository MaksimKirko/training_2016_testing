package com.github.maximkirko.testing.web.converter.entitytomodel;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.web.model.GradeModel;
import com.github.maximkirko.testing.web.model.QuizModel;
import com.github.maximkirko.testing.web.model.UserModel;

public class GradeToModel implements Converter<Grade, GradeModel> {

	@Override
	public GradeModel convert(Grade grade) {

		GradeModel model = new GradeModel();
		model.setId(grade.getId());
		model.setMark(grade.getMark());
		UserModel user = new UserModel();
		user.setId(grade.getUser().getId());
		model.setUser(user);
		QuizModel quiz = new QuizModel();
		quiz.setId(grade.getQuiz().getId());
		model.setQuiz(quiz);
		return model;
	}

}
