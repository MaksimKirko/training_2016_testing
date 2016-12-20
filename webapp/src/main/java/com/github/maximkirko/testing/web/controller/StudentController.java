package com.github.maximkirko.testing.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
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
import com.github.maximkirko.testing.services.IUserService;
import com.github.maximkirko.testing.web.model.GradeModel;
import com.github.maximkirko.testing.web.model.UserModel;
import com.github.maximkirko.testing.web.utils.WebUtils;

@RestController
@RequestMapping("/users")
public class StudentController {

	@Inject
	private IUserService userService;

	@Inject
	protected ConversionService conversionService;

	public StudentController() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserModel>> getAll() {

		List<User> all = userService.getAll();

		if (all == null) {
			return new ResponseEntity<List<UserModel>>(HttpStatus.NOT_FOUND);
		}

		List<UserModel> converted = new ArrayList<>();
		for (User entity : all) {
			entity.setPassword(null);
			converted.add(conversionService.convert(entity, UserModel.class));
		}

		return new ResponseEntity<List<UserModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getById(@PathVariable Long entityId,
			@RequestHeader(value = "Authorization") String authHeader) {

		User user = userService.get(entityId);
		if (user == null) {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}

		UserModel model = conversionService.convert(user, UserModel.class);

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);

		if (!model.getEmail().equals(currentUser.getEmail())) {
			model.setPassword(null);
		}

		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Void> updateUserData(@RequestBody UserModel entityModel,
			@RequestHeader(value = "Authorization") String authHeader) {

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);
		if (currentUser == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		currentUser = conversionService.convert(entityModel, User.class);
		userService.save(currentUser);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestHeader(value = "Authorization") String authHeader) {

		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/withGrades/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getWithGrades(@PathVariable Long entityId,
			@RequestHeader(value = "Authorization") String authHeader) {

		User user = userService.get(entityId);
		if (user == null) {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}

		UserModel model = conversionService.convert(user, UserModel.class);

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);

		if (!model.getEmail().equals(currentUser.getEmail())) {
			model.setPassword(null);
		}

		List<Grade> grades = user.getGrades();
		List<GradeModel> gradeModels = new ArrayList<>();
		if (grades != null) {
			for (Grade grade : grades) {
				gradeModels.add(conversionService.convert(grade, GradeModel.class));
			}
			model.setGrades(gradeModels);
		}
		model.setGrades(gradeModels);

		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}

	@RequestMapping(value = "/withRole/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getWithRole(@PathVariable Long entityId,
			@RequestHeader(value = "Authorization") String authHeader) {

		User user = userService.getWithRole(entityId);
		if (user == null) {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}

		UserModel model = conversionService.convert(user, UserModel.class);

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);

		if (!model.getEmail().equals(currentUser.getEmail())) {
			model.setPassword(null);
		}

		return new ResponseEntity<UserModel>(conversionService.convert(user, UserModel.class), HttpStatus.OK);
	}

}
