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

import com.github.maximkirko.testing.datamodel.models.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class SubjectServiceTest {

	@Inject
	private ISubjectService subjectService;

	private Long id;

	public void prepare() {

		Subject subject = new Subject();
		subject.setTitle("test subject " + new Random().nextInt());

		try {
			id = subjectService.save(subject);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test
	public void getByIdTest() {

		prepare();

		Subject subject = subjectService.get(id);

		Assert.assertNotNull(String.format("subject for id=%s should not be null", id), subject);
		Assert.assertEquals(id, subject.getId());

		subjectService.delete(id);
	}

	@Test
	public void saveTest() {

		Subject answer = new Subject();
		answer.setTitle("insertTest subject " + new Random().nextInt());

		Long id = null;

		try {
			id = subjectService.save(answer);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
			return;
		}

		Assert.assertNotNull(String.format("subject for id=%s should not be null", id), id);
		Assert.assertEquals(answer, subjectService.get(id));

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

		prepare();

		subjectService.delete(id);

		Assert.assertNull(String.format("subject for id=%s should be null", id), subjectService.get(id));
	}
}
