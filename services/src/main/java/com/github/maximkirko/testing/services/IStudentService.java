package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.datamodel.users.Student;

public interface IStudentService {

	Student get(Long id);

	List getAll();

	Long save(Student user);

	void saveAll(List<Student> users);

	void delete(Long id);
}
