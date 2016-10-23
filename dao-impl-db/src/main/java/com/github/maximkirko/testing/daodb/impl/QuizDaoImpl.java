package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.QuizDao;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class QuizDaoImpl implements QuizDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public Quiz get(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from quiz where id = ?",
                new Object[] { id }, new BeanPropertyRowMapper<Quiz>(
                		Quiz.class));
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Quiz> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Quiz entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Quiz entity) {
        // TODO Auto-generated method stub

    }

}
