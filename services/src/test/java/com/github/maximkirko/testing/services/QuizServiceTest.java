package com.github.maximkirko.testing.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizServiceTest {

	@Inject
	private IQuizService quizService;

	@Inject
	private ISubjectService subjectService;

	@Inject
	private IQuestionService questionService;

	private Long id;
	private List<Long> idList;

	private void prepareOne() {

		Quiz quiz = new Quiz();
		quiz.setTitle("test quiz " + new Random().nextInt());
		quiz.setDescription("test quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("quizTest subject");
		subject.setDescription("quizTest description");
		
		quiz.setSubject(subject);
		
		List<Question> questions = new ArrayList<Question>();
		Question question = new Question();
		question.setText("quizTest question");
		question.setHint("quizTest hint");
		questions.add(question);

		quiz.setQuestions(questions);

		try {
			id = quizService.save(quiz);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
	}

	private void prepareMany() {

		List<Quiz> quizzes = new ArrayList<Quiz>();

		Subject subject = new Subject();
		subject.setTitle("quizTest subject");
		subject.setDescription("quizTest description");
		
		for (int i = 0; i < 10; i++) {
			Quiz quiz = new Quiz();
			quiz.setTitle("test quiz " + i);
			quiz.setDescription("test quiz " + i);
			
			quiz.setSubject(subject);
			
			quizzes.add(quiz);
		}

		idList = quizService.saveAll(quizzes);
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		Quiz quiz = quizService.get(id);

		Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quiz);
		Assert.assertEquals(id, quiz.getId());

		quizService.delete(id);
	}

//	@Test
//	public void getWithQuestions() {
//
//		prepareOne();
//
//		Quiz quiz = quizService.getWithQuestions(id);
//
//		Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quiz);
//		Assert.assertNotNull(String.format("questions for quiz id=%s should not be null", id), quiz.getQuestions());
//		Assert.assertEquals(id, quiz.getId());
//
//		quizService.delete(id);
//
//	}
//
//	@Test
//	public void getAllTest() {
//
//		prepareMany();
//
//		List<Quiz> quizs = quizService.getAll();
//
//		int i = 0;
//		for (Long id : idList) {
//
//			Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quizs.get(i));
//			Assert.assertEquals(id, quizs.get(i).getId());
//
//			quizService.delete(quizs.get(i).getId());
//			i++;
//		}
//	}
//
//	@Test
//	public void saveTest() {
//
//		Quiz quiz = new Quiz();
//		quiz.setTitle("insertTest quiz " + new Random().nextInt());
//		quiz.setDescription("insertTest quiz " + new Random().nextInt());
//
//		Long id = null;
//
//		try {
//			id = quizService.save(quiz);
//		} catch (DuplicateKeyException e) {
//			System.out.println(e.getStackTrace());
//			return;
//		}
//
//		Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), id);
//		Assert.assertEquals(quiz, quizService.get(id));
//
//		quizService.delete(id);
//
//	}
//
//	@Test
//	public void saveMultipleTest() {
//
//		List<Quiz> quizs = new ArrayList<Quiz>();
//
//		for (int i = 0; i < 10; i++) {
//			Quiz quiz = new Quiz();
//			quiz.setTitle("multiple test quiz " + i);
//			quiz.setDescription("multiple test quiz " + i);
//			quizs.add(quiz);
//		}
//
//		List<Long> idList = quizService.saveAll(quizs);
//
//		int i = 0;
//		for (Long id : idList) {
//
//			Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), id);
//			Assert.assertEquals(quizs.get(i), quizService.get(id));
//
//			quizService.delete(id);
//			i++;
//		}
//	}
//
//	@Test
//	public void deleteTest() {
//
//		prepareOne();
//
//		quizService.delete(id);
//
//		Assert.assertNull(String.format("quiz for id=%s should be null", id), quizService.get(id));
//	}
}
