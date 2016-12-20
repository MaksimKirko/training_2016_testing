package com.github.maximkirko.testing.web.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.services.IQuestionService;
import com.github.maximkirko.testing.web.model.QuestionModel;

@RestController
@RequestMapping("/questions")
public class QuestionController extends GenericController<Question, QuestionModel> {

	@Inject
	private IQuestionService questionService;

	public QuestionController() {

		super.service = questionService;
		super.entityClass = Question.class;
		super.modelClass = QuestionModel.class;
	}

	@Override
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewEntity(@RequestBody QuestionModel entityModel) {

		return super.createNewEntity(entityModel);
	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateEntity(@RequestBody QuestionModel entityModel, @PathVariable Long entityId) {

		return super.updateEntity(entityModel, entityId);
	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long entityId) {
		return super.delete(entityId);
	}

	@RequestMapping(value = "/withAnswers/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionModel> getWithAnswers(@PathVariable Long entityId) {

		Question question = questionService.getWithAnswers(entityId);
		if (question == null) {
			return new ResponseEntity<QuestionModel>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<QuestionModel>(conversionService.convert(question, QuestionModel.class),
				HttpStatus.OK);
	}

}
