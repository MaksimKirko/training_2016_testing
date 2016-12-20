package com.github.maximkirko.testing.web.converter.modeltoentity;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.web.model.GradeModel;

public class ModelToGrade implements Converter<GradeModel, Grade> {

	private ModelToUser modelToUser = new ModelToUser();
	private ModelToQuiz modelToQuiz = new ModelToQuiz();

	@Override
	public Grade convert(GradeModel model) {

		Grade grade = new Grade();
		grade.setId(model.getId());
		grade.setMark(model.getMark());
		User user = modelToUser.convert(model.getUser());
		grade.setUser(user);
		Quiz quiz = modelToQuiz.convert(model.getQuiz());
		grade.setQuiz(quiz);
		return grade;
	}

}
