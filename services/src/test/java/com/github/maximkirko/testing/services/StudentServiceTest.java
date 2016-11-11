package com.github.maximkirko.testing.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.users.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class StudentServiceTest {

	@Inject
	private IStudentService studentService;

	@Inject
	private IGradeService gradeService;

	@Test
	@Ignore
	public void getByIdTest() {
		Long id = 1l;

		Student student = studentService.get(id);
		System.out.println(student.getGrades().get(0).getMark());

		Assert.assertNotNull("student for id=%s should not be null", student);
		Assert.assertEquals(id, student.getId());
	}

	@Test

	public void insertTest() {

		// List<Grade> grades = new ArrayList<Grade>();
		// Grade grade = gradeService.get(1l);
		// grades.add(grade);

		Student student = new Student();
		student.setFirstName("Bill");
		student.setLastName("Gates");
		student.setAge(21);
		student.setCourse(122);
		student.setEmail("microsoft@bing.com");
		student.setPassword("windows");
		// student.setGrades(grades);

		Long id = studentService.save(student);

		Assert.assertNotNull("student for id=%s should not be null", id);
		Assert.assertEquals(student, studentService.get(id));
	}

	@Test
	@Ignore
	public void deleteTest() {
		Long id = 1l;

		studentService.delete(id);
	}
}
