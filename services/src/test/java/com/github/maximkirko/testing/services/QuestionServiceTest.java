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
public class QuestionServiceTest {

	@Inject
	private IQuestionService questionService;

	private Long id;
	private List<Long> idList;

	private void prepare() {

		Question question = new Question();
		question.setText("test question " + new Random().nextInt());

		List<Answer> answers = new ArrayList<Answer>();
		Answer answer = new Answer();
		answer.setText("questionTest answer");
		answers.add(answer);

		question.setAnswers(answers);

		try {
			id = questionService.save(question);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getStackTrace());
		}
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
	
	
	@Test
	public void updateTest() {
		
		//prepare();
		
		Question question = questionService.getWithAnswers(1l);
		
		Answer answer = new Answer();
		answer.setText("new answer");
		List<Answer> answers = new ArrayList<>();
		answers.add(answer);
		
		question.setAnswers(answers);
		
		questionService.save(question);
		
	}

//	@Test
//	public void getByIdTest() {
//
//		prepare();
//
//		Question question = questionService.get(id);
//
//		Assert.assertNotNull(String.format("question for id=%s should not be null", id), question);
//		Assert.assertEquals(id, question.getId());
//
//		questionService.delete(id);
//	}
//
//	@Test
//	public void getWithAnswers() {
//
//		prepare();
//
//		Question question = questionService.getWithAnswers(id);
//
//		Assert.assertNotNull(String.format("question for id=%s should not be null", id), question);
//		Assert.assertNotNull(String.format("answers for question id=%s should not be null", id), question.getAnswers());
//		Assert.assertEquals(id, question.getId());
//
//		questionService.delete(id);
//	}
//
//	@Test
//	public void getAllTest() {
//
//		prepareMany();
//
//		List<Question> questions = questionService.getAll();
//
//		int i = 0;
//		for (Long id : idList) {
//
//			Assert.assertNotNull(String.format("question for id=%s should not be null", id), questions.get(i));
//			Assert.assertEquals(id, questions.get(i).getId());
//
//			questionService.delete(questions.get(i).getId());
//			i++;
//		}
//	}
//
//	@Test
//	public void saveTest() {
//
//		Question question = new Question();
//		question.setText("insertTest question " + new Random().nextInt());
//
//		Long id = null;
//
//		try {
//			id = questionService.save(question);
//		} catch (DuplicateKeyException e) {
//			System.out.println(e.getStackTrace());
//			return;
//		}
//
//		Assert.assertNotNull(String.format("question for id=%s should not be null", id), id);
//		Assert.assertEquals(question, questionService.get(id));
//
//		questionService.delete(id);
//
//	}
//
//	@Test
//	public void saveMultipleTest() {
//
//		List<Question> questions = new ArrayList<Question>();
//
//		for (int i = 0; i < 10; i++) {
//			Question question = new Question();
//			question.setText("multiple test question " + i);
//			questions.add(question);
//		}
//
//		List<Long> idList = questionService.saveAll(questions);
//
//		int i = 0;
//		for (Long id : idList) {
//
//			Assert.assertNotNull(String.format("question for id=%s should not be null", id), id);
//			Assert.assertEquals(questions.get(i), questionService.get(id));
//
//			questionService.delete(id);
//			i++;
//		}
//	}
//
//	@Test
//	public void deleteTest() {
//
//		prepare();
//
//		questionService.delete(id);
//
//		Assert.assertNull(String.format("question for id=%s should be null", id), questionService.get(id));
//	}
}
