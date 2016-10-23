package com.github.maximkirko.testing.daodb;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface QuizDao {

    Quiz get(Long id);

    void insert(Quiz entity);

    void update(Quiz entity);

    void delete(Long id);

    List<Quiz> getAll();
}
