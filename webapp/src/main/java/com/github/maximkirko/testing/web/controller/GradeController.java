package com.github.maximkirko.testing.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.services.IGradeService;
import com.github.maximkirko.testing.web.model.GradeModel;
import com.github.maximkirko.testing.web.model.QuizModel;
import com.github.maximkirko.testing.web.model.UserModel;

@RestController
@RequestMapping("/grades")
public class GradeController extends GenericController<Grade, GradeModel> {

	@Inject
	private IGradeService gradeService;

	public GradeController() {

		super.service = gradeService;
		super.entityClass = Grade.class;
		super.modelClass = GradeModel.class;
	}

	@RequestMapping(value = "/withUserAndQuiz/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<GradeModel> getWithGrades(@PathVariable Long entityId) {

		Grade grade = gradeService.getWithUserAndQuiz(entityId);
		GradeModel model = conversionService.convert(grade, GradeModel.class);
		
		UserModel user = conversionService.convert(grade.getUser(), UserModel.class);
		QuizModel quiz = conversionService.convert(grade.getQuiz(), QuizModel.class);
		
		model.setUser(user);
		model.setQuiz(quiz);
		
		return new ResponseEntity<GradeModel>(model, HttpStatus.OK);
	}

	@RequestMapping(value = "/byUserId/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<List<GradeModel>> getByUserId(@PathVariable Long entityId) {

		List<Grade> grades = gradeService.getByUserId(entityId);
		List<GradeModel> converted = new ArrayList<>();
		for (Grade grade : grades) {
			converted.add(conversionService.convert(grade, modelClass));
		}

		return new ResponseEntity<List<GradeModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/byQuizId/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<List<GradeModel>> getByQuizId(@PathVariable Long entityId) {

		List<Grade> grades = gradeService.getByQuizId(entityId);
		List<GradeModel> converted = new ArrayList<>();
		for (Grade grade : grades) {
			converted.add(conversionService.convert(grade, modelClass));
		}

		return new ResponseEntity<List<GradeModel>>(converted, HttpStatus.OK);
	}
}
