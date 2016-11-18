package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Student;

public interface IGradeDao extends IGenericDao<Grade, Long> {

	Grade getWithStudentAndQuiz(Long id);
	
	List<Grade> getByStudent(Student student);
	
	List<Grade> getByQuiz(Quiz quiz);
	
}
