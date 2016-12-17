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

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;
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

	@Inject
	private IRoleService roleService;

	private Long id;
	private Long userId;
	private Long subjectId;
	private List<Long> idList;

	private void prepareOne() {

		User user = new User();
		user.setFirstName("gradeTest user");
		user.setLastName("gradeTest user");
		user.setAge(20);
		user.setCourse("gradeTest course");
		user.setEmail("gradeTest email " + new Random().nextInt());
		user.setPassword("gradeTest password " + new Random().nextInt());
		user.setRole(roleService.getByType(RoleEnum.STUDENT));
		userId = userService.save(user);

		Subject subject = new Subject();
		subject.setTitle("gradeTest subject");
		subject.setDescription("gradeTest description");
		subjectId = subjectService.save(subject);

		Quiz quiz = new Quiz();
		quiz.setTitle("gradeTest quiz " + new Random().nextInt());
		quiz.setDescription("gradeTest quiz " + new Random().nextInt());
		quiz.setSubject(subject);
		quizService.save(quiz);

		Grade grade = new Grade();
		grade.setMark(9.0f);
		grade.setQuiz(quiz);
		grade.setUser(user);

		try {
			id = gradeService.save(grade);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}
	}

	private void prepareMany() {

		List<Grade> grades = new ArrayList<Grade>();

		User user = new User();
		user.setFirstName("gradeTest user");
		user.setLastName("gradeTest user");
		user.setAge(20);
		user.setCourse("gradeTest course");
		user.setEmail("gradeTest email " + new Random().nextInt());
		user.setPassword("gradeTest password " + new Random().nextInt());
		user.setRole(roleService.getByType(RoleEnum.STUDENT));
		userId = userService.save(user);

		Quiz quiz = new Quiz();
		quiz.setTitle("gradeTest quiz " + new Random().nextInt());
		quiz.setDescription("gradeTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("gradeTest subject");
		subject.setDescription("gradeTest description");
		subjectId = subjectService.save(subject);

		quiz.setSubject(subject);
		quizService.save(quiz);

		for (int i = 0; i < 10; i++) {
			Grade grade = new Grade();
			grade.setMark(new Random().nextFloat());

			grade.setQuiz(quiz);
			grade.setUser(user);
		}

		idList = gradeService.saveAll(grades);
	}

	@After
	public void clear() {
		subjectService.delete(subjectId);
		userService.delete(userId);
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		Grade grade = gradeService.get(id);

		Assert.assertNotNull(String.format("grade for id=%s should not be null", id), grade);
		Assert.assertNotNull(String.format("user for grade id=%s should not be null", id), grade.getUser());
		Assert.assertNotNull(String.format("quiz for grade id=%s should not be null", id), grade.getQuiz());
		Assert.assertEquals(id, grade.getId());
	}

	@Test
	public void getAllTest() {

		prepareMany();

		List<Grade> grades = gradeService.getAll();

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("grade for id=%s should not be null", id), grades.get(i));
			Assert.assertEquals(id, grades.get(i).getId());
			i++;
		}
	}

	@Test
	public void saveTest() {

		User user = new User();
		user.setFirstName("gradeTest user");
		user.setLastName("gradeTest user");
		user.setAge(20);
		user.setCourse("gradeTest course");
		user.setEmail("gradeTest email " + new Random().nextInt());
		user.setPassword("gradeTest password " + new Random().nextInt());
		user.setRole(roleService.getByType(RoleEnum.STUDENT));
		userId = userService.save(user);

		Subject subject = new Subject();
		subject.setTitle("gradeTest subject");
		subject.setDescription("gradeTest description");
		subjectId = subjectService.save(subject);

		Quiz quiz = new Quiz();
		quiz.setTitle("gradeTest quiz " + new Random().nextInt());
		quiz.setDescription("gradeTest quiz " + new Random().nextInt());
		quiz.setSubject(subject);
		quizService.save(quiz);

		Grade grade = new Grade();
		grade.setMark(9.0f);
		grade.setQuiz(quiz);
		grade.setUser(user);

		try {
			id = gradeService.save(grade);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(String.format("grade for id=%s should not be null", id), id);
		Assert.assertEquals(grade, gradeService.get(id));

		gradeService.delete(id);

	}

	@Test
	public void saveMultipleTest() {

		List<Grade> grades = new ArrayList<Grade>();

		User user = new User();
		user.setFirstName("gradeTest user");
		user.setLastName("gradeTest user");
		user.setAge(20);
		user.setCourse("gradeTest course");
		user.setEmail("gradeTest email " + new Random().nextInt());
		user.setPassword("gradeTest password " + new Random().nextInt());
		user.setRole(roleService.getByType(RoleEnum.STUDENT));
		userId = userService.save(user);

		Quiz quiz = new Quiz();
		quiz.setTitle("gradeTest quiz " + new Random().nextInt());
		quiz.setDescription("gradeTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("gradeTest subject");
		subject.setDescription("gradeTest description");
		subjectId = subjectService.save(subject);

		quiz.setSubject(subject);
		quizService.save(quiz);

		for (int i = 0; i < 10; i++) {
			Grade grade = new Grade();
			grade.setMark(new Random().nextFloat());

			grade.setQuiz(quiz);
			grade.setUser(user);
		}

		idList = gradeService.saveAll(grades);

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("grade for id=%s should not be null", id), gradeService.get(id));
			Assert.assertEquals(grades.get(i), gradeService.get(id));

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
