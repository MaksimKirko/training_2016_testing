package com.github.maximkirko.testing.services;

import java.util.Random;

import javax.inject.Inject;

import static org.mockito.Mockito.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Answer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class AnswerServiceTest {

	/*
	 * TODO: make tests for all services
	 */
	
	@Inject
	private IAnswerService answerService;

	private Long id;

	public void prepare() {

		Answer answer = new Answer();
		answer.setText("test answer " + new Random().nextInt());

		try {
			id = answerService.save(answer);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test
	public void getByIdTest() {

		prepare();

		Answer answer = answerService.get(id);

		Assert.assertNotNull("answer for id=%s should not be null", answer);
		Assert.assertEquals(id, answer.getId());

		answerService.delete(id);

		// IAnswerService service = mock(IAnswerService.class);
		// Answer answer = mock(Answer.class);
		// answer = service.get(id);
		//
		// verify(service).get(id);
	}

	@Test
	public void insertTest() {

		Answer answer = new Answer();
		answer.setText("insert test answer " + new Random().nextInt());
		
		Long id = null;
		
		try {
			id = answerService.save(answer);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
			return;
		}

		Assert.assertNotNull(String.format("answer for id=%s should not be null", id), id);
		Assert.assertEquals(answer, answerService.get(id));

		answerService.delete(id);

	}

	@Test
	public void deleteTest() {

		prepare();

		answerService.delete(id);

		Assert.assertNull(String.format("answer for id=%s should be null", id), id);
	}
}
