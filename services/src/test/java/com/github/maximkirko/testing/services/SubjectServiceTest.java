package com.github.maximkirko.testing.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class SubjectServiceTest {

	@Inject
	private ISubjectService subjectService;

	@Test	
	@Ignore
	public void getByIdTest() {
		Long id = 1l;

		Subject subject = subjectService.get(id);

		Assert.assertNotNull("subject for id=%s should not be null", subject);
		Assert.assertEquals(id, subject.getId());
	}

	@Test		
	public void insertTest() {

//		Subject subject = new Subject();
//		subject.setTitle("Математика");
//		subject.setDescription("some desc");
		
		Subject subject = subjectService.get(1l);
		subject.setTitle("Физика");

		Long id = subjectService.save(subject);
	}
}
