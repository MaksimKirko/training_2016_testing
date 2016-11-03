package com.github.maximkirko.testing.services;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizServiceTest {

    @Inject
    private QuizService quizService;

    @Test
    public void getAllTest() {
    	
    	System.out.println(Quiz.class.getAnnotation(DBTable.class).name());
    	
//    	List quizzes = quizService.getAll();
//    	for (Object object : quizzes) {
//			System.out.println(object.toString());
//		}
    	
    }

}
