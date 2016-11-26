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

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class AnswerServiceTest {

	@Inject
	private IAnswerService answerService;
	
	private Long id;
	private List<Long> idList;
	
	public void prepareOne() {

		Answer answer = new Answer();
		answer.setText("test answer " + new Random().nextInt());
		
		List<Question> questions = new ArrayList<Question>();
		Question question = new Question();
		question.setText("answerTest question");
		question.setHint("answerTest hint");
		questions.add(question);
		
		answer.setQuestions(questions);
		
		try {
			id = answerService.save(answer);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void prepareMany() {
		
		List<Answer> answers = new ArrayList<Answer>();

		for (int i = 0; i < 10; i++) {
			Answer answer = new Answer();
			answer.setText("multi-test answer " + i);
			answers.add(answer);
		}

		idList = answerService.saveAll(answers);
	}

	@Test
	public void getByIdTest() {

		prepareOne();
		
		Answer answer = answerService.get(id);

		Assert.assertNotNull(String.format("answer for id=%s should not be null", id), answer);
		Assert.assertEquals(id, answer.getId());

		answerService.delete(id);
	}

	@Test
	public void getWithQuestions() {
		
		prepareOne();

		Answer answer = answerService.getWithQuestions(id);

		Assert.assertNotNull(String.format("answer for id=%s should not be null", id), answer);
		Assert.assertNotNull(String.format("questions for answer id=%s should not be null", id), answer.getQuestions());
		Assert.assertEquals(id, answer.getId());

		answerService.delete(id);
		
	}
	
	@Test
	public void getAllTest() {
		
		prepareMany();
		
		List<Answer> answers = answerService.getAll();

		int i = 0;
		for (Long id : idList) {
			
			Assert.assertNotNull(String.format("answer for id=%s should not be null", id), answers.get(i));
			Assert.assertEquals(id, answers.get(i).getId());

			answerService.delete(answers.get(i).getId());
			i++;
		}
	}
	
	@Test
	public void saveTest() {

		Answer answer = new Answer();
		answer.setText("insertTest answer " + new Random().nextInt());

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
	public void saveMultipleTest() {

		List<Answer> answers = new ArrayList<Answer>();

		for (int i = 0; i < 10; i++) {
			Answer answer = new Answer();
			answer.setText("multiple test answer " + i);
			answers.add(answer);
		}

		List<Long> idList = answerService.saveAll(answers);
		
		int i = 0;
		for (Long id : idList) {
			
			Assert.assertNotNull(String.format("answer for id=%s should not be null", id), id);
			Assert.assertEquals(answers.get(i), answerService.get(id));
			
			answerService.delete(id);
			i++;
		}
	}

	@Test
	public void deleteTest() {

		prepareOne();

		answerService.delete(id);

		Assert.assertNull(String.format("answer for id=%s should be null", id), answerService.get(id));
	}
}
