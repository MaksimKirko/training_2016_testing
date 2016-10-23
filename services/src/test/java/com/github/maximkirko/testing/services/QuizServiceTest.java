package com.github.maximkirko.testing.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizServiceTest {

    @Inject
    private QuizService quizService;

    @Test
    public void getByIdtest() {
        Quiz quiz = quizService.get(2l);
        System.out.println(quiz.toString());

//        Assert.assertNotNull("quiz for id=2 should not be null", quiz);
//        Assert.assertEquals(new Long(2), quiz.getId());
    }

}
