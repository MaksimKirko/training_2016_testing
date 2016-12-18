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

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.services.IQuizService;
import com.github.maximkirko.testing.web.model.QuestionModel;
import com.github.maximkirko.testing.web.model.QuizModel;
import com.github.maximkirko.testing.web.model.SubjectModel;

@RestController
@RequestMapping("/quizzes")
public class QuizController extends GenericController<Quiz, QuizModel> {

	@Inject
	private IQuizService quizService;

	public QuizController() {

		super.service = quizService;
		super.entityClass = Quiz.class;
		super.modelClass = QuizModel.class;
	}

	@RequestMapping(value = "/bySubjectId/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<List<QuizModel>> getBySubjectId(@PathVariable Long entityId) {

		List<Quiz> quizzes = quizService.getBySubjectId(entityId);
		List<QuizModel> converted = new ArrayList<>();
		for (Quiz quiz : quizzes) {
			converted.add(conversionService.convert(quiz, modelClass));
		}

		return new ResponseEntity<List<QuizModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/withSubject/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<QuizModel> getWithSubjects(@PathVariable Long entityId) {

		Quiz quiz = quizService.getWithSubject(entityId);
		QuizModel model = conversionService.convert(quiz, QuizModel.class);

		Subject subject = quiz.getSubject();
		SubjectModel subjectModel = new SubjectModel();
		subjectModel.setId(subject.getId());
		model.setSubject(subjectModel);

		return new ResponseEntity<QuizModel>(model, HttpStatus.OK);
	}

	@RequestMapping(value = "/withQuestions/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<QuizModel> getWithQuestions(@PathVariable Long entityId) {

		Quiz quiz = quizService.getWithQuestions(entityId);
		QuizModel model = conversionService.convert(quiz, QuizModel.class);

		List<Question> questions = quiz.getQuestions();
		List<QuestionModel> questionModels = new ArrayList<>();
		if (questions != null) {
			for (Question question : questions) {
				questionModels.add(conversionService.convert(question, QuestionModel.class));
			}
			model.setQuestions(questionModels);
		}

		return new ResponseEntity<QuizModel>(model, HttpStatus.OK);
	}

}
