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
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.datamodel.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class UserServiceTest {

	@Inject
	private IUserService userService;

	@Inject
	private IRoleService roleService;

	@Inject
	private IQuizService quizService;

	@Inject
	private ISubjectService subjectService;

	@Inject
	private IGradeService gradeService;

	private Long id;
	private Long subjectId;

	private void prepareOne() {

		User user = new User();
		user.setFirstName("test user");
		user.setLastName("test user");
		user.setAge(20);
		user.setCourse("test course");
		user.setEmail("test email " + new Random().nextInt());
		user.setPassword("test password " + new Random().nextInt());
		user.setRole(roleService.getByType(RoleEnum.STUDENT));

		try {
			id = userService.save(user);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		Subject subject = new Subject();
		subject.setTitle("userTest subject");
		subject.setDescription("userTest description");
		subjectId = subjectService.save(subject);
		
		Quiz quiz = new Quiz();
		quiz.setTitle("userTest quiz " + new Random().nextInt());
		quiz.setDescription("userTest quiz " + new Random().nextInt());
		quiz.setSubject(subject);
		quizService.save(quiz);

		Grade grade = new Grade();
		grade.setMark(9.0f);
		grade.setQuiz(quiz);
		grade.setUser(user);
		gradeService.save(grade);
	}

	@After
	public void clear() {
		subjectService.delete(subjectId);
		userService.delete(id);
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		User user = userService.get(id);

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), user);
		Assert.assertEquals(id, user.getId());
	}

	@Test
	public void getWithGradesTest() {

		prepareOne();

		User user = userService.getWithGrades(id);

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), user);
		Assert.assertNotNull(String.format("grades for user id=%s should not be null", id), user.getGrades());
		Assert.assertEquals(id, user.getId());
	}

	@Test
	public void getWithRoleTest() {

		prepareOne();

		User user = userService.getWithRole(id);

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), user);
		Assert.assertNotNull(String.format("role for user id=%s should not be null", id), user.getRole());
		Assert.assertEquals(id, user.getId());
	}

	@Test
	public void saveTest() {

		User user = new User();
		user.setFirstName("insertTest user");
		user.setLastName("insertTest user");
		user.setAge(20);
		user.setCourse("insertTest course");
		user.setEmail("insertTest email " + new Random().nextInt());
		user.setPassword("insertTest password " + new Random().nextInt());
		user.setRole(roleService.getByType(RoleEnum.STUDENT));

		try {
			id = userService.save(user);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		Subject subject = new Subject();
		subject.setTitle("userTest subject");
		subject.setDescription("userTest description");
		subjectId = subjectService.save(subject);
		
		Quiz quiz = new Quiz();
		quiz.setTitle("userTest quiz " + new Random().nextInt());
		quiz.setDescription("userTest quiz " + new Random().nextInt());
		quiz.setSubject(subject);
		quizService.save(quiz);

		Grade grade = new Grade();
		grade.setMark(9.0f);
		grade.setQuiz(quiz);
		grade.setUser(user);
		gradeService.save(grade);

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), userService.get(id));
		Assert.assertEquals(user, userService.get(id));
	}

	@Test
	public void deleteTest() {

		prepareOne();

		userService.delete(id);

		Assert.assertNull(String.format("user for id=%s should be null", id), userService.get(id));
	}
}
