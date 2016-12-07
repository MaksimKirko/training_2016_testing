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
	private List<Long> idList;
	private Grade testGrade;
	private Quiz testQuiz;
	private Subject testSubject;

	private void prepareOne() {

		User user = new User();
		user.setFirstName("test user");
		user.setLastName("test user");
		user.setAge(20);
		user.setCourse("test course");
		user.setEmail("test email " + new Random().nextInt());
		user.setPassword("test password " + new Random().nextInt());
		user.setRole(roleService.getByType(RoleEnum.USER));

		Grade grade = new Grade();
		grade.setMark(9.0f);

		Quiz quiz = new Quiz();
		quiz.setTitle("userTest quiz " + new Random().nextInt());
		quiz.setDescription("userTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("userTest subject");
		subject.setDescription("userTest description");
		subject.setQuizzes(Arrays.asList(new Quiz[] { quiz }));
		testSubject = subject;

		quiz.setSubject(subject);
		Long id = quizService.save(quiz);
		testQuiz = quizService.get(id);

		grade.setQuiz(testQuiz);

		try {
			this.id = userService.save(user);
			user.setId(this.id);
			grade.setUser(user);
			testGrade = grade;
			testGrade.setId(gradeService.save(grade));
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
	}

	private void prepareMany() {

		List<User> users = new ArrayList<User>();

		for (int i = 0; i < 10; i++) {

			User user = new User();
			user.setFirstName("test user " + i);
			user.setLastName("test user");
			user.setAge(20);
			user.setCourse("test course");
			user.setEmail("test email " + i);
			user.setPassword("test password " + new Random().nextInt());
			user.setRole(roleService.getByType(RoleEnum.USER));
			users.add(user);
		}

		idList = userService.saveAll(users);
	}

	@After
	public void deleteHelpers() {
		
		Subject subject = subjectService.getByTitle(testSubject.getTitle());
		subjectService.delete(subject.getId());
		gradeService.delete(testGrade.getId());
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		User user = userService.get(id);

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), user);
		Assert.assertEquals(id, user.getId());

		userService.delete(id);
	}

	@Test
	public void getWithGradesTest() {

		prepareOne();

		User user = userService.getWithGrades(id);

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), user);
		Assert.assertNotNull(String.format("grades for user id=%s should not be null", id), user.getGrades());
		Assert.assertEquals(id, user.getId());

		userService.delete(id);

	}

	@Test
	public void getWithRoleTest() {

		prepareOne();

		User user = userService.getWithRole(id);

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), user);
		Assert.assertNotNull(String.format("role for user id=%s should not be null", id), user.getRole());
		Assert.assertEquals(id, user.getId());

		userService.delete(id);
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
		user.setRole(roleService.getByType(RoleEnum.USER));

		Grade grade = new Grade();
		grade.setMark(9.0f);

		Quiz quiz = new Quiz();
		quiz.setTitle("insertTest quiz " + new Random().nextInt());
		quiz.setDescription("insertTest quiz " + new Random().nextInt());

		Subject subject = new Subject();
		subject.setTitle("insertTest subject");
		subject.setDescription("insertTest description");
		subject.setQuizzes(Arrays.asList(new Quiz[] { quiz }));
		testSubject = subject;

		quiz.setSubject(subject);
		Long id = quizService.save(quiz);
		testQuiz = quizService.get(id);

		grade.setQuiz(testQuiz);

		id = null;

		try {
			id = userService.save(user);
			user.setId(id);
			grade.setUser(user);
			testGrade = grade;
			testGrade.setId(gradeService.save(grade));
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
			return;
		}

		Assert.assertNotNull(String.format("user for id=%s should not be null", id), id);
		Assert.assertEquals(user, userService.getWithRole(id));

		userService.delete(id);

	}

	@Test
	public void deleteTest() {

		prepareOne();

		userService.delete(id);

		Assert.assertNull(String.format("user for id=%s should be null", id), userService.get(id));
	}
}
