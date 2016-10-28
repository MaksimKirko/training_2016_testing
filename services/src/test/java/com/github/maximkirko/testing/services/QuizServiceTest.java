package com.github.maximkirko.testing.services;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizServiceTest {

    @Inject
    private QuizService quizService;

    @Test
    public void getAllTest() {
    	
    	List quizzes = quizService.getAll();
    	for (Object object : quizzes) {
			System.out.println(object.toString());
		}
    	
    }

}
