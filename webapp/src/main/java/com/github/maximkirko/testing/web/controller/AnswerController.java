package com.github.maximkirko.testing.web.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
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

}
