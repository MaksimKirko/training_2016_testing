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

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.users.Student;
import com.github.maximkirko.testing.datamodel.users.StudentDetails;

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
		System.out.println(student.getDetails().getEmail());
		System.out.println(student.getGrades().get(0).getMark());
		
		Assert.assertNotNull("student for id=%s should not be null", student);
		Assert.assertEquals(id, student.getId());
	}

	@Test
	
	public void insertTest() {

		StudentDetails sd = new StudentDetails();
		sd.setAge(21);
		sd.setCourse(122);
		sd.setEmail("microsoft@bing.com");
		sd.setPassword("windows");
		
//		List<Grade> grades = new ArrayList<Grade>();
//		Grade grade = gradeService.get(1l);
//		grades.add(grade);
		
		Student student = new Student();
		student.setFirstName("Bill");
		student.setLastName("Gates");
		student.setDetails(sd);
		//student.setGrades(grades);
		
		Long id = studentService.save(student);
	}

	@Test
	@Ignore
	public void deleteTest() {
		Long id = 1l;

		studentService.delete(id);
	}
}
