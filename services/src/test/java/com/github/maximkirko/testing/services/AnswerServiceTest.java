package com.github.maximkirko.testing.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

	@Inject
	private IQuestionService questionService;

	private Long id;
	private Question question;

	@Before
	public void prepare() {
		
		question = new Question();
		question.setText("answerTest question");
		question.setHint("answerTest hint");

		try {
			id = questionService.save(question);
			question.setId(id);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		List<Answer> answers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Answer answer = new Answer();
			answer.setText("test answer " + i);
			answer.setCorrectness(false);
			answer.setQuestion(question);
			answers.add(answer);
		}

		question.setAnswers(answers);
		questionService.save(question);
	}

	@After
	public void clear() {
		questionService.delete(id);
	}

	@Test
	public void getByIdTest() {

		Long id = question.getAnswers().get(0).getId();

		Answer answer = answerService.get(id);

		Assert.assertNotNull(String.format("answer for id=%s should not be null", id), answer);
		Assert.assertEquals(id, answer.getId());
	}

	@Test
	public void getByQuestionIdTest() {

		List<Answer> qAnswers = question.getAnswers();
		List<Answer> answers = answerService.getByQuestionId(id);
		
		int i = 0;
		for (Answer answer : answers) {
			Assert.assertNotNull(String.format("answer for id=%s should not be null", qAnswers.get(i).getId()), answer);
			Assert.assertEquals(qAnswers.get(i).getId(), answer.getId());
			i++;
		}
	}

	@Test
	public void saveTest() {

		Answer answer = new Answer();
		answer.setText("insertTest answer " + new Random().nextInt());
		answer.setCorrectness(true);
		answer.setQuestion(question);

		Long id = null;

		try {
			id = answerService.save(answer);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(String.format("answer for id=%s should not be null", id), id);
		Assert.assertEquals(answer, answerService.get(id));

	}

	@Test
	public void saveMultipleTest() {

		List<Answer> answers = new ArrayList<Answer>();

		for (int i = 0; i < 10; i++) {
			Answer answer = new Answer();
			answer.setText("multiple test answer " + i);
			answer.setCorrectness(false);
			answer.setQuestion(question);
			answers.add(answer);
		}

		List<Long> idList = answerService.saveAll(answers);

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("answer for id=%s should not be null", id), id);
			Assert.assertEquals(answers.get(i), answerService.get(id));

			i++;
		}
	}

	@Test
	public void deleteTest() {

		Long id = question.getAnswers().get(0).getId();
		answerService.delete(id);

		Assert.assertNull(String.format("answer for id=%s should be null", id), answerService.get(id));
	}
}
