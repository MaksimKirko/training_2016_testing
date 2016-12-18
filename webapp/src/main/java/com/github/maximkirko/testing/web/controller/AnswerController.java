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

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.web.model.AnswerModel;

@RestController
@RequestMapping("/answers")
public class AnswerController extends GenericController<Answer, AnswerModel> {

	@Inject
	private IAnswerService answerService;

	public AnswerController() {

		super.service = answerService;
		super.entityClass = Answer.class;
		super.modelClass = AnswerModel.class;

	}

	@RequestMapping(value = "/byQuestion/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<List<AnswerModel>> getByQuestionId(@PathVariable Long entityId) {

		List<Answer> answers = answerService.getByQuestionId(entityId);

		List<AnswerModel> converted = new ArrayList<>();
		for (Answer answer : answers) {
			converted.add(conversionService.convert(answer, modelClass));
		}

		return new ResponseEntity<List<AnswerModel>>(converted, HttpStatus.OK);
	}

}
