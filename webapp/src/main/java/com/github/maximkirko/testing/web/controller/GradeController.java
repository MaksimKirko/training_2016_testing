package com.github.maximkirko.testing.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.services.IAuthenticationService;
import com.github.maximkirko.testing.services.IGradeService;
import com.github.maximkirko.testing.services.IUserService;
import com.github.maximkirko.testing.web.model.GradeModel;
import com.github.maximkirko.testing.web.model.QuizModel;
import com.github.maximkirko.testing.web.model.UserModel;
import com.github.maximkirko.testing.web.utils.WebUtils;

@RestController
@RequestMapping("/grades")
public class GradeController extends GenericController<Grade, GradeModel> {

	@Inject
	private IGradeService gradeService;

	@Inject
	private IUserService userService;

	@Inject
	private IAuthenticationService authenticationService;

	public GradeController() {

		super.service = gradeService;
		super.entityClass = Grade.class;
		super.modelClass = GradeModel.class;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<GradeModel>> getAll(@RequestHeader(value = "Authorization") String authHeader) {

		ResponseEntity<List<GradeModel>> response = super.getAll(authHeader);

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);

		if (!authenticationService.validateUserRole(currentUser.getEmail())) {
			for (GradeModel model : response.getBody()) {
				UserModel user = model.getUser();
				user.setPassword(null);
				model.setUser(user);
			}
		}

		return response;
	}

	@Override
	@RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<GradeModel> getById(@PathVariable Long entityId,
			@RequestHeader(value = "Authorization") String authHeader) {

		ResponseEntity<GradeModel> response = super.getById(entityId, authHeader);

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);

		if (!authenticationService.validateUserRole(currentUser.getEmail())) {
			GradeModel model = response.getBody();
			UserModel user = model.getUser();
			user.setPassword(null);
			model.setUser(user);
		}

		return response;
	}

	@Override
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewEntity(@RequestBody GradeModel entityModel) {

		return super.createNewEntity(entityModel);
	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateEntity(@RequestBody GradeModel entityModel, @PathVariable Long entityId) {

		return super.updateEntity(entityModel, entityId);
	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long entityId) {

		return super.delete(entityId);
	}

	@RequestMapping(value = "/withUserAndQuiz/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<GradeModel> getWithGrades(@PathVariable Long entityId) {

		Grade grade = gradeService.getWithUserAndQuiz(entityId);
		if (grade == null) {
			return new ResponseEntity<GradeModel>(HttpStatus.NOT_FOUND);
		}

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
		if (grades == null) {
			return new ResponseEntity<List<GradeModel>>(HttpStatus.NOT_FOUND);
		}

		List<GradeModel> converted = new ArrayList<>();
		for (Grade grade : grades) {
			converted.add(conversionService.convert(grade, modelClass));
		}

		return new ResponseEntity<List<GradeModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/byQuizId/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<List<GradeModel>> getByQuizId(@PathVariable Long entityId) {

		List<Grade> grades = gradeService.getByQuizId(entityId);
		if (grades == null) {
			return new ResponseEntity<List<GradeModel>>(HttpStatus.NOT_FOUND);
		}

		List<GradeModel> converted = new ArrayList<>();
		for (Grade grade : grades) {
			converted.add(conversionService.convert(grade, modelClass));
		}

		return new ResponseEntity<List<GradeModel>>(converted, HttpStatus.OK);
	}
}
