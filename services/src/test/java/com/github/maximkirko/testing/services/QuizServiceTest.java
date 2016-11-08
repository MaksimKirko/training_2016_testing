package com.github.maximkirko.testing.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizServiceTest {

	@Inject
	private IQuizService quizService;

	@Inject
	private ISubjectService subjectService;
	
	@Test
	public void getByIdTest() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		Long id = 1l;
		
		Quiz quiz = quizService.get(id);
		
		for (Question question : quiz.getQuestions()) {
			System.out.println(question.toString());
		}
		
		Assert.assertNotNull("quiz for id=%s should not be null", quiz);
		Assert.assertEquals(id, quiz.getId());
	}
	
	@Test	
	@Ignore
	public void insertTest() {
		
		Quiz quiz = new Quiz();
		quiz.setTitle("Present Simple");
		quiz.setSubject(subjectService.get(1l));

		Long id = quizService.save(quiz);
	}

	@Test
	@Ignore
	public void saveQuizTest() {
		Quiz quiz = new Quiz();
		quiz.setTitle("test title");

		Long id = quizService.save(quiz);

		Assert.assertNotNull(id);

		Quiz quizFromDb = quizService.get(id);

		Assert.assertEquals(quiz.getTitle(), quizFromDb.getTitle());
	}

	@Test
	@Ignore
	public void saveQuizMultipleTest() {
		List<Quiz> quizzes = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Quiz quiz = new Quiz();
			quiz.setTitle("test title" + i);
			quizzes.add(quiz);
		}

		quizService.saveAll(quizzes);
	}

}
