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
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.services.IRoleService;
import com.github.maximkirko.testing.services.IUserService;
import com.github.maximkirko.testing.web.model.GradeModel;
import com.github.maximkirko.testing.web.model.UserModel;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<User, UserModel> {

	@Inject
	private IUserService userService;

	@Inject
	private IRoleService roleService;

	public UserController() {

		super.service = userService;
		super.entityClass = User.class;
		super.modelClass = UserModel.class;
	}

//	@RequestMapping(value = "/byEmail/{entityEmail}", method = RequestMethod.GET)
//	public ResponseEntity<UserModel> getByEmail(@PathVariable String entityEmail) {
//
//		User user = userService.getByEmail(entityEmail);
//		return new ResponseEntity<UserModel>(conversionService.convert(user, modelClass), HttpStatus.OK);
//	}

	@RequestMapping(value = "/withGrades/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getWithGrades(@PathVariable Long entityId) {

		User user = userService.getWithGrades(entityId);
		UserModel model = conversionService.convert(user, UserModel.class);

		List<Grade> grades = user.getGrades();
		List<GradeModel> gradeModels = new ArrayList<>();
		if (grades != null) {
			for (Grade grade : grades) {
				gradeModels.add(super.conversionService.convert(grade, GradeModel.class));
			}
			model.setGrades(gradeModels);
		}
		model.setGrades(gradeModels);

		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}

	@RequestMapping(value = "/byRole/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<List<UserModel>> getByRole(@PathVariable Long entityId) {

		Role role = roleService.get(entityId);
		List<User> users = userService.getByRole(role);

		List<UserModel> converted = new ArrayList<>();
		for (User user : users) {
			converted.add(conversionService.convert(user, modelClass));
		}

		return new ResponseEntity<List<UserModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/withRole/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getWithRole(@PathVariable Long entityId) {

		User user = userService.getWithRole(entityId);
		return new ResponseEntity<UserModel>(conversionService.convert(user, modelClass), HttpStatus.OK);
	}

}