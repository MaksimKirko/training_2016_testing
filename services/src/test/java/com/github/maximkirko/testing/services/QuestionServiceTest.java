package com.github.maximkirko.testing.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuestionServiceTest {
	
	@Inject
	private IQuestionService questionService;

	@Test
	public void getByIdTest() {
		Long id = 1l;

		Question question = questionService.get(id);

		Assert.assertNotNull("question for id=%s should not be null", question);
		Assert.assertEquals(id, question.getId());
	}

	@Test
	@Ignore
	public void insertTest() {

		Question question = new Question();
		question.setText("some question");
		question.setHint("some hint");

		Long id = questionService.save(question);
	}
}
