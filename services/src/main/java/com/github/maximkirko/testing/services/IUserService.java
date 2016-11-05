package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.datamodel.users.User;

public interface IUserService {

	User get(Long id);

	List getAll();

	Long save(User user);

	void saveAll(List<User> users);

	void delete(Long id);
}
