package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;

public interface IUserDao extends IGenericDao<User, Long> {
	
	User getByEmail(String email);
	
	User getWithRole(Long id);
	
	List<User> getByRole(Role role);
	
}
