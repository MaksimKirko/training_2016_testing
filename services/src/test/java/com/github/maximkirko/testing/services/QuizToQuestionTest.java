package com.github.maximkirko.testing.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizToQuestionTest {

	@Inject
	private IQuizToQuestionService quizToQuestionService;

	@Inject
	private IQuizService quizService;

	@Inject
	private IQuestionService questionService;

	@Test
	public void insertTest() {

		Quiz quiz = quizService.get(1l);
		
		List<Question> questions = new ArrayList<Question>();
		questions.add(questionService.get(1l));

		quiz.setQuestions(questions);
		
		quizToQuestionService.save(quiz);
	}

	@Test
	@Ignore
	public void deleteByQuizIdTest() {
		
		Long id = 1l;
		
		quizToQuestionService.deleteByQuizId(id);
	}
	
	@Test
	@Ignore
	public void deleteByQuestionIdTest() {
		
		Long id = 1l;
		
		quizToQuestionService.deleteByQuestionId(id);
	}
}
