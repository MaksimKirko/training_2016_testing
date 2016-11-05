package com.github.maximkirko.testing.daodb.impl;

import com.github.maximkirko.testing.daodb.IUserDetailsDao;
import com.github.maximkirko.testing.datamodel.users.UserDetails;

public class UserDetailsDaoImpl extends GenericDaoImpl<UserDetails> implements IUserDetailsDao {
	public UserDetailsDaoImpl() {
		super(UserDetails.class);
	}

}
