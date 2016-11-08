package com.github.maximkirko.testing.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Grade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class GradeServiceTest {
	
	@Inject
	private IGradeService gradeService;

	@Inject
	private IQuizService quizService;
	
	@Inject
	private IStudentService studentService;
	
	@Test
	@Ignore
	public void getByIdTest() {
		Long id = 1l;

		Grade grade = gradeService.get(id);

		Assert.assertNotNull("answer for id=%s should not be null", grade);
		Assert.assertEquals(id, grade.getId());
	}

	@Test
	
	public void insertTest() {

		Grade grade = new Grade();
		grade.setMark(9.3f);
		grade.setQuiz(quizService.get(1l));
		grade.setStudent(studentService.get(1l));

		Long id = gradeService.save(grade);
		
		Assert.assertNotNull("grade for id=%s should not be null", id);
		Assert.assertEquals(grade, gradeService.get(id));
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		Long id = 1l;
		
		gradeService.delete(id);
		
		Assert.assertNull("grade for id=%s should not be null", gradeService.get(id));
	}
}
