package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;

public interface IUserService extends IGenericService<User, Long> {

	User getByEmail(String email);
	
	User getWithRole(Long id);
	
	List<User> getByRole(Role role);
	
	User getWithGrades(Long id);
	
}
