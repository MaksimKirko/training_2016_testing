package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IUserDao;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;

@Repository
public class UserDaoXmlImpl extends GenericDaoXmlImpl<User, Long> implements IUserDao {

	public UserDaoXmlImpl() throws IOException {
		super(User.class);
	}

	@Override
	public User getWithRole(Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getByRole(Role role) {

		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public User getByEmail(String email) {
		// TODO
		throw new UnsupportedOperationException();
	}
}
