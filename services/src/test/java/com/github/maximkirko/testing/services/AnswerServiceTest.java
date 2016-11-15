package com.github.maximkirko.testing.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Answer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class AnswerServiceTest {

	@Inject
	private IAnswerService answerService;

	private Long id;
	
	@Before
	public void prepare() {

		Answer answer = new Answer();
		answer.setText("test answer");

		try {
			id = answerService.save(answer);
		} catch (DuplicateKeyException e) {
			
			id = 1l;
			return;
		}
	}

	@Test
	public void getByIdTest() {

		Answer answer = answerService.get(id);

		Assert.assertNotNull("answer for id=%s should not be null", answer);
		Assert.assertEquals(id, answer.getId());
	}
	
	@Test
	public void insertTest() {

		Answer answer = new Answer();
		answer.setText("some answer");

		id = answerService.save(answer);

		Assert.assertNotNull("answer for id=%s should not be null", id);
		Assert.assertEquals(answer, answerService.get(id));
	}

	@Test
	public void deleteTest() {

		answerService.delete(id);

		Assert.assertNull("answer for id=%s should not be null", answerService.get(id));
	}
}
