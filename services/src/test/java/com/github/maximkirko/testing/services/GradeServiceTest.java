package com.github.maximkirko.testing.services;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.datamodel.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class GradeServiceTest {

	@Inject
	private IGradeService gradeService;

	@Inject
	private IQuizService quizService;

	@Inject
	private ISubjectService subjectService;

	@Inject
	private IUserService userService;

	private Long id;
	private List<Long> idList;
	private Quiz testQuiz;
	private Subject testSubject;

	private void prepareOne() {

		Grade grade = new Grade();
		grade.setMark(9.0f);

		Quiz quiz = new Quiz();
		quiz.setTitle("gradeTest quiz " + new Random().nextInt());
		quiz.setDescription("gradeTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("gradeTest subject");
		subject.setDescription("gradeTest description");
		subject.setQuizzes(Arrays.asList(new Quiz[] { quiz }));

		quiz.setSubject(subject);
		Long id = quizService.save(quiz);
		testQuiz = quizService.get(id);
		testSubject = subject;

		User user = userService.get(2l);

		grade.setQuiz(testQuiz);
		grade.setUser(user);

		try {
			this.id = gradeService.save(grade);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
	}

	private void prepareMany() {

		List<Grade> grades = new ArrayList<Grade>();

		Quiz quiz = new Quiz();
		quiz.setTitle("gradeTest quiz " + new Random().nextInt());
		quiz.setDescription("gradeTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("gradeTest subject");
		subject.setDescription("gradeTest description");
		subject.setQuizzes(Arrays.asList(new Quiz[] { quiz }));
		testSubject = subject;

		quiz.setSubject(subject);
		Long id = quizService.save(quiz);
		testQuiz = quizService.get(id);

		User user = userService.get(2l);

		for (int i = 0; i < 10; i++) {
			Grade grade = new Grade();
			grade.setMark(new Random().nextFloat());

			grade.setQuiz(testQuiz);
			grade.setUser(user);
		}

		idList = gradeService.saveAll(grades);
	}

	@After
	public void deleteSubject() {
		Subject subject = subjectService.getByTitle(testSubject.getTitle());
		subjectService.delete(subject.getId());
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		Grade grade = gradeService.get(id);

		Assert.assertNotNull(String.format("grade for id=%s should not be null", id), grade);
		Assert.assertEquals(id, grade.getId());

		gradeService.delete(id);
	}

	@Test
	public void getWithUserAndQuizTest() {

		prepareOne();

		Grade grade = gradeService.getWithUserAndQuiz(id);

		Assert.assertNotNull(String.format("grade for id=%s should not be null", id), grade);
		Assert.assertNotNull(String.format("user for grade id=%s should not be null", id), grade.getUser());
		Assert.assertNotNull(String.format("quiz for grade id=%s should not be null", id), grade.getQuiz());
		Assert.assertEquals(id, grade.getId());

		gradeService.delete(id);

	}

	@Test
	public void getAllTest() {

		prepareMany();

		List<Grade> grades = gradeService.getAll();

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("grade for id=%s should not be null", id), grades.get(i));
			Assert.assertEquals(id, grades.get(i).getId());

			gradeService.delete(grades.get(i).getId());
			i++;
		}
	}

	@Test
	public void saveTest() {

		Grade grade = new Grade();
		grade.setMark(9.0f);

		Quiz quiz = new Quiz();
		quiz.setTitle("insertGradeTest quiz " + new Random().nextInt());
		quiz.setDescription("insertGradeTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("insertGradeTest subject");
		subject.setDescription("insertGradeTest description");
		subject.setQuizzes(Arrays.asList(new Quiz[] { quiz }));

		quiz.setSubject(subject);
		Long id = quizService.save(quiz);
		testQuiz = quizService.get(id);
		testSubject = subject;

		User user = userService.get(2l);

		grade.setQuiz(testQuiz);
		grade.setUser(user);

		id = null;

		try {
			id = gradeService.save(grade);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
			return;
		}

		Assert.assertNotNull(String.format("grade for id=%s should not be null", id), id);
		Assert.assertEquals(grade, gradeService.getWithUserAndQuiz(id));

		gradeService.delete(id);

	}

	@Test
	public void saveMultipleTest() {

		List<Grade> grades = new ArrayList<Grade>();

		Quiz quiz = new Quiz();
		quiz.setTitle("insertGradeTest quiz " + new Random().nextInt());
		quiz.setDescription("insertGradeTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("insertGradeTest subject");
		subject.setDescription("insertGradeTest description");
		subject.setQuizzes(Arrays.asList(new Quiz[] { quiz }));

		quiz.setSubject(subject);
		Long id1 = quizService.save(quiz);
		testQuiz = quizService.get(id1);
		testSubject = subject;

		User user = userService.get(2l);

		for (int i = 0; i < 10; i++) {
			Grade grade = new Grade();
			grade.setMark(9.0f);
			grade.setQuiz(testQuiz);
			grade.setUser(user);

		}

		List<Long> idList = gradeService.saveAll(grades);

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("grade for id=%s should not be null", id), id);
			Assert.assertEquals(grades.get(i), gradeService.getWithUserAndQuiz(id));

			gradeService.delete(id);
			i++;
		}
	}

	@Test
	public void deleteTest() {

		prepareOne();

		gradeService.delete(id);

		Assert.assertNull(String.format("grade for id=%s should be null", id), gradeService.get(id));
	}
}
