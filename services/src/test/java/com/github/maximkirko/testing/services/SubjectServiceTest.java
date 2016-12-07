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

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class SubjectServiceTest {

	@Inject
	private ISubjectService subjectService;
	
	@Inject
	private IQuizService quizService;

	private Long id;
	private List<Long> idList;

	public void prepareOne() {

		Subject subject = new Subject();
		subject.setTitle("test subject " + new Random().nextInt());

		Quiz quiz = new Quiz();
		quiz.setTitle("subjectTest quiz " + new Random().nextInt());
		quiz.setDescription("subjectTest quiz " + new Random().nextInt());
		quiz.setSubject(subject);

		List<Quiz> quizzes = new ArrayList<>();
		quizzes.add(quiz);
		quizService.saveAll(quizzes);
		
		subject.setQuizzes(quizzes);

		try {
			id = subjectService.save(subject);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void prepareMany() {

		List<Subject> subjects = new ArrayList<Subject>();

		for (int i = 0; i < 10; i++) {
			Subject subject = new Subject();
			subject.setTitle("multi-test subject " + i);
			subjects.add(subject);
		}

		idList = subjectService.saveAll(subjects);
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		Subject subject = subjectService.get(id);

		Assert.assertNotNull(String.format("subject for id=%s should not be null", id), subject);
		Assert.assertEquals(id, subject.getId());

		subjectService.delete(id);
	}

	@Test
	public void getWithQuizzes() {

		prepareOne();

		Subject subject = subjectService.getWithQuizzes(id);

		Assert.assertNotNull(String.format("subject for id=%s should not be null", id), subject);
		Assert.assertNotNull(String.format("quizzes for answer id=%s should not be null", id), subject.getQuizzes());
		Assert.assertEquals(id, subject.getId());

		subjectService.delete(id);

	}

	@Test
	public void getAllTest() {

		prepareMany();

		List<Subject> subjects = subjectService.getAll();

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("subject for id=%s should not be null", id), subjects.get(i));
			Assert.assertEquals(id, subjects.get(i).getId());

			subjectService.delete(subjects.get(i).getId());
			i++;
		}
	}

	@Test
	public void saveTest() {

		Subject subject = new Subject();
		subject.setTitle("insertTest subject " + new Random().nextInt());

		Long id = null;

		try {
			id = subjectService.save(subject);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
			return;
		}

		Assert.assertNotNull(String.format("subject for id=%s should not be null", id), id);

		subjectService.delete(id);

	}

	@Test
	public void saveMultipleTest() {

		List<Subject> subjects = new ArrayList<Subject>();

		for (int i = 0; i < 10; i++) {
			Subject subject = new Subject();
			subject.setTitle("multiple test subject " + i);
			subjects.add(subject);
		}

		List<Long> idList = subjectService.saveAll(subjects);

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("subject for id=%s should not be null", id), id);
			Assert.assertEquals(subjects.get(i), subjectService.get(id));

			subjectService.delete(id);
			i++;
		}
	}

	@Test
	public void deleteTest() {

		prepareOne();

		subjectService.delete(id);

		Assert.assertNull(String.format("subject for id=%s should be null", id), subjectService.get(id));
	}
}
