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

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.services.IAuthenticationService;
import com.github.maximkirko.testing.services.IUserService;
import com.github.maximkirko.testing.web.model.AnswerModel;
import com.github.maximkirko.testing.web.utils.WebUtils;

@RestController
@RequestMapping("/answers")
public class AnswerController extends GenericController<Answer, AnswerModel> {

	@Inject
	private IAnswerService answerService;

	@Inject
	private IUserService userService;

	@Inject
	private IAuthenticationService authenticationService;

	public AnswerController() {

		super.service = answerService;
		super.entityClass = Answer.class;
		super.modelClass = AnswerModel.class;

	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AnswerModel>> getAll(@RequestHeader(value = "Authorization") String authHeader) {

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);

		ResponseEntity<List<AnswerModel>> response = super.getAll(authHeader);

		if (!authenticationService.validateUserRole(currentUser.getEmail())) {
			for (AnswerModel model : response.getBody()) {
				model.setCorrectness(false);
			}
		}
		return response;
	}

	@Override
	@RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<AnswerModel> getById(@PathVariable Long entityId,
			@RequestHeader(value = "Authorization") String authHeader) {

		String username = WebUtils.getCurrentUserName(authHeader);
		User currentUser = userService.getByEmail(username);

		ResponseEntity<AnswerModel> response = super.getById(entityId, authHeader);

		if (!authenticationService.validateUserRole(currentUser.getEmail())) {
			response.getBody().setCorrectness(false);
		}
		return response;
	}

	@Override
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewEntity(@RequestBody AnswerModel entityModel) {

		return super.createNewEntity(entityModel);
	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateEntity(@RequestBody AnswerModel entityModel, @PathVariable Long entityId) {

		return super.updateEntity(entityModel, entityId);
	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long entityId) {

		return super.delete(entityId);
	}

	@RequestMapping(value = "/byQuestion/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<List<AnswerModel>> getByQuestionId(@PathVariable Long entityId) {

		List<Answer> answers = answerService.getByQuestionId(entityId);
		if (answers == null) {
			return new ResponseEntity<List<AnswerModel>>(HttpStatus.NOT_FOUND);
		}

		List<AnswerModel> converted = new ArrayList<>();
		for (Answer answer : answers) {
			converted.add(conversionService.convert(answer, modelClass));
		}

		return new ResponseEntity<List<AnswerModel>>(converted, HttpStatus.OK);
	}

}
