package com.github.maximkirko.testing.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Answer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class AnswerServiceTest {
	
	@Inject
	private IAnswerService answerService;

	@Test
	@Ignore
	public void getByIdTest() {
		Long id = 1l;

		Answer answer = answerService.get(id);

		Assert.assertNotNull("answer for id=%s should not be null", answer);
		Assert.assertEquals(id, answer.getId());
	}

	@Test
	public void insertTest() {

		Answer answer = new Answer();
		answer.setText("some answer");

		Long id = answerService.save(answer);
	}
}
