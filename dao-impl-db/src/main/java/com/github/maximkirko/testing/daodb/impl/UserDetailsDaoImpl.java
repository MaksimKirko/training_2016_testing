package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IUserDetailsDao;
import com.github.maximkirko.testing.datamodel.users.UserDetails;

@Repository
public class UserDetailsDaoImpl extends GenericDaoImpl<UserDetails> implements IUserDetailsDao {
	public UserDetailsDaoImpl() {
		super(UserDetails.class);
	}

}
