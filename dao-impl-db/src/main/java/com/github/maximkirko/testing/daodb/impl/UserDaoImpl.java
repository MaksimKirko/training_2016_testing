package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IUserDao;
import com.github.maximkirko.testing.datamodel.users.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {
	public UserDaoImpl() {
		super(User.class);
	}

}
