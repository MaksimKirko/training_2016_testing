package com.github.maximkirko.testing.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizServiceTest {

	@Inject
	private IQuizService quizService;

	@Inject
	private ISubjectService subjectService;

	private Long id;
	private Long subjectId;
	private List<Long> idList;

	private void prepareOne() {

		Quiz quiz = new Quiz();
		quiz.setTitle("test quiz " + new Random().nextInt());
		quiz.setDescription("test quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("quizTest subject");
		subject.setDescription("quizTest description");

		try {
			subjectId = subjectService.save(subject);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		quiz.setSubject(subject);
		id = quizService.save(quiz);
	}

	private void prepareMany() {

		List<Quiz> quizzes = new ArrayList<Quiz>();

		Subject subject = new Subject();
		subject.setTitle("quizTest subject");
		subject.setDescription("quizTest description");

		try {
			subjectId = subjectService.save(subject);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			Quiz quiz = new Quiz();
			quiz.setTitle("test quiz " + i);
			quiz.setDescription("test quiz " + i);

			quiz.setSubject(subject);

			quizzes.add(quiz);
		}

		idList = quizService.saveAll(quizzes);
	}

	@After
	public void clear() {
		subjectService.delete(subjectId);
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		Quiz quiz = quizService.get(id);

		Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quiz);
		Assert.assertEquals(id, quiz.getId());
	}

	@Test
	public void getWithSubject() {

		prepareOne();

		Quiz quiz = quizService.getWithSubject(id);

		Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quiz);
		Assert.assertNotNull(String.format("subject for quiz id=%s should not be null", subjectId), quiz.getSubject());
		Assert.assertEquals(id, quiz.getId());

	}

	@Test
	public void getBySubject() {

		prepareMany();

		List<Quiz> quizzes = quizService.getBySubjectId(subjectId);

		int i = 0;
		for (Long id : idList) {
			Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quizzes.get(i));
			i++;
		}
	}

	@Test
	public void getWithQuestions() {

		prepareOne();

		Quiz quiz = quizService.getWithQuestions(id);

		Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quiz);
		Assert.assertNotNull(String.format("questions for quiz id=%s should not be null", id), quiz.getQuestions());
		Assert.assertEquals(id, quiz.getId());

	}

	@Test
	public void getAllTest() {

		prepareMany();

		List<Quiz> quizzes = quizService.getAll();

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), quizzes.get(i));
			Assert.assertEquals(id, quizzes.get(i).getId());

			quizService.delete(quizzes.get(i).getId());
			i++;
		}

	}

	@Test
	public void saveTest() {

		Quiz quiz = new Quiz();
		quiz.setTitle("insertTest quiz " + new Random().nextInt());
		quiz.setDescription("insertTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("insertQuizTest subject");
		subject.setDescription("insertQuizTest description");
		subjectId = subjectService.save(subject);

		quiz.setSubject(subject);
		Long id = null;

		try {
			id = quizService.save(quiz);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
			return;
		}

		Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), id);
		Assert.assertEquals(quiz, quizService.getWithSubject(id));

	}

	@Test
	public void saveMultipleTest() {

		List<Quiz> quizzes = new ArrayList<Quiz>();

		Subject subject = new Subject();
		subject.setTitle("insertQuizTest subject");
		subject.setDescription("insertQuizTest description");
		subjectId = subjectService.save(subject);

		for (int i = 0; i < 10; i++) {
			Quiz quiz = new Quiz();
			quiz.setTitle("multiple test quiz " + i);
			quiz.setDescription("multiple test quiz " + i);
			quiz.setSubject(subject);
			quizzes.add(quiz);
		}

		List<Long> idList = quizService.saveAll(quizzes);

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("quiz for id=%s should not be null", id), id);
			Assert.assertEquals(quizzes.get(i), quizService.getWithSubject(id));

			quizService.delete(id);
			i++;
		}
	}

	@Test
	public void deleteTest() {

		prepareOne();

		quizService.delete(id);

		Assert.assertNull(String.format("quiz for id=%s should be null", id), quizService.get(id));
	}
}
