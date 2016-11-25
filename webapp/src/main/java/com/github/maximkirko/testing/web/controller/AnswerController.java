package com.github.maximkirko.testing.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.services.IAnswerService;
import com.github.maximkirko.testing.web.converter.AnswerToModel;
import com.github.maximkirko.testing.web.converter.ModelToAnswer;
import com.github.maximkirko.testing.web.model.AnswerModel;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	@Inject
	private IAnswerService service;

	private AnswerToModel answerConverter;

	private ModelToAnswer modelConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AnswerModel>> getAll() {

		List<Answer> all = service.getAll();

		List<AnswerModel> converted = new ArrayList<>();
		for (Answer answer : all) {
			converted.add(answerConverter.convert(answer));
		}

		return new ResponseEntity<List<AnswerModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{answerId}", method = RequestMethod.GET)
	public ResponseEntity<AnswerModel> getById(@PathVariable Long answerId) {

		Answer answer = service.get(answerId);
		return new ResponseEntity<AnswerModel>(answerConverter.convert(answer), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewAnswer(@RequestBody AnswerModel answerModel) {

		service.save(modelConverter.convert(answerModel));
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{answerId}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateAnswer(@RequestBody AnswerModel answerModel, @PathVariable Long answerId) {

		Answer answer = modelConverter.convert(answerModel);
		answer.setId(answerId);
		service.save(answer);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{answerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long answerId) {

		service.delete(answerId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
