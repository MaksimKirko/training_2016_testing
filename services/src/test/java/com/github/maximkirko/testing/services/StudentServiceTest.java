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

	@Test
	@Ignore
	public void getByIdTest() {
		Long id = 1l;

		Student user = studentService.get(id);

		Assert.assertNotNull("user for id=%s should not be null", user);
		Assert.assertEquals(id, user.getId());
	}

	@Test
	public void insertTest() {

		Student student = new Student();
		student.setFirstName("Bill");
		student.setLastName("Gates");

		Long id = studentService.save(student);
	}

	@Test
	@Ignore
	public void deleteTest() {
		Long id = 1l;

		studentService.delete(id);
	}
}
