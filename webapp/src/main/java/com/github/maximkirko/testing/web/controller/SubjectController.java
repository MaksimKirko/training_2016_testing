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

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.services.ISubjectService;
import com.github.maximkirko.testing.web.model.QuizModel;
import com.github.maximkirko.testing.web.model.SubjectModel;

@RestController
@RequestMapping("/subjects")
public class SubjectController extends GenericController<Subject, SubjectModel> {

	@Inject
	private ISubjectService subjectService;

	public SubjectController() {

		super.service = subjectService;
		super.entityClass = Subject.class;
		super.modelClass = SubjectModel.class;
	}

	@Override
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewEntity(@RequestBody SubjectModel entityModel) {

		return super.createNewEntity(entityModel);

	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateEntity(@RequestBody SubjectModel entityModel, @PathVariable Long entityId) {

		return super.updateEntity(entityModel, entityId);
	}

	@Override
	@RequestMapping(value = "/admin/{entityId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long entityId) {

		service.delete(entityId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/byTitle/{entityTitle}", method = RequestMethod.GET)
	public ResponseEntity<SubjectModel> getByTitle(@PathVariable String entityTitle) {

		Subject subject = subjectService.getByTitle(entityTitle);
		if (subject == null) {
			return new ResponseEntity<SubjectModel>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SubjectModel>(conversionService.convert(subject, modelClass), HttpStatus.OK);
	}

	@RequestMapping(value = "/withQuizzes/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<SubjectModel> getWithQuizs(@PathVariable Long entityId) {

		Subject subject = subjectService.getWithQuizzes(entityId);

		if (subject == null) {
			return new ResponseEntity<SubjectModel>(HttpStatus.NOT_FOUND);
		}

		SubjectModel model = conversionService.convert(subject, SubjectModel.class);

		List<Quiz> quizzes = subject.getQuizzes();
		List<QuizModel> quizModels = new ArrayList<>();
		if (quizzes != null) {
			for (Quiz quiz : quizzes) {
				quizModels.add(super.conversionService.convert(quiz, QuizModel.class));
			}
			model.setQuizzes(quizModels);
		}
		model.setQuizzes(quizModels);

		return new ResponseEntity<SubjectModel>(model, HttpStatus.OK);
	}

}
