package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface QuizService {

    void saveAll(List<Quiz> quizzes);

    void save(Quiz quiz);

    Quiz get(Long id);

}
