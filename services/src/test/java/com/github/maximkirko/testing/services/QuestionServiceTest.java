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

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuestionServiceTest {
	
	@Inject
	private IQuestionService questionService;
	
	@Inject
	private IAnswerService answerService;

	@Test
	@Ignore
	public void getByIdTest() {
		Long id = 1l;

		Question question = questionService.get(id);

		System.out.println(question);
		
		Assert.assertNotNull("question for id=%s should not be null", id);
		Assert.assertEquals(id, question.getId());
	}

	@Test	
	
	public void insertTest() {

		Question question = new Question();
		question.setText("some question4");
		question.setHint("some hint");
		
//		Question question = questionService.get(1l);
//		question.setText("updated question");
		
		List<Answer> answers = new ArrayList<Answer>();
		Answer answer = answerService.get(1l);
		answers.add(answer);
		
		question.setAnswers(answers);

		Long id = questionService.save(question);
		
		Assert.assertNotNull("question for id=%s should not be null", id);
		Assert.assertEquals(question, questionService.get(id));
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		Long id = 7l;
		
		questionService.delete(id);
		
		Assert.assertNull("question for id=%s should be null", questionService.get(id));
	}
}
