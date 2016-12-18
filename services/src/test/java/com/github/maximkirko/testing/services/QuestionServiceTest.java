package com.github.maximkirko.testing.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.junit.After;
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
public class QuestionServiceTest {

	@Inject
	private IQuestionService questionService;

	private Long id;
	private List<Long> idList;

	private void prepareOne() {

		Question question = new Question();
		question.setText("test question");
		question.setHint("test hint");

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

	private void prepareMany() {

		List<Question> questions = new ArrayList<Question>();

		for (int i = 0; i < 10; i++) {
			Question question = new Question();
			question.setText("multi-test question " + i);
			questions.add(question);
		}

		idList = questionService.saveAll(questions);
	}

	@After
	public void clear() {
		questionService.delete(id);
	}

	@Test
	public void getByIdTest() {

		prepareOne();

		Question question = questionService.get(id);

		Assert.assertNotNull(String.format("question for id=%s should not be null", id), question);
		Assert.assertEquals(id, question.getId());
	}

	@Test
	public void getWithAnswers() {

		prepareOne();

		Question question = questionService.getWithAnswers(id);

		Assert.assertNotNull(String.format("question for id=%s should not be null", id), question);
		Assert.assertNotNull(String.format("answers for question id=%s should not be null", id), question.getAnswers());
		Assert.assertEquals(id, question.getId());
	}

	@Test
	public void getAllTest() {

		prepareMany();

		List<Question> questions = questionService.getAll();

		for (Question question : questions) {
			Assert.assertNotNull(String.format("question for id=%s should not be null", id), question);
		}
		
		for(Long id : idList) {
			questionService.delete(id);
		}
	}

	@Test
	public void saveTest() {

		Question question = new Question();
		question.setText("insertTest question " + new Random().nextInt());

		try {
			id = questionService.save(question);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			return;
		}

		Assert.assertNotNull(String.format("question for id=%s should not be null", id), id);
		Assert.assertEquals(question, questionService.get(id));
	}

	@Test
	public void saveMultipleTest() {

		List<Question> questions = new ArrayList<Question>();

		for (int i = 0; i < 10; i++) {
			Question question = new Question();
			question.setText("multiple test question " + i);
			questions.add(question);
		}

		List<Long> idList = questionService.saveAll(questions);

		int i = 0;
		for (Long id : idList) {

			Assert.assertNotNull(String.format("question for id=%s should not be null", id), id);
			Assert.assertEquals(questions.get(i), questionService.get(id));
			questionService.delete(id);
			i++;
		}
	}

	@Test
	public void deleteTest() {

		prepareOne();

		questionService.delete(id);

		Assert.assertNull(String.format("question for id=%s should be null", id), questionService.get(id));
	}
}
