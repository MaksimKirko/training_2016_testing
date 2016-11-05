package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IUserDetailsDao;
import com.github.maximkirko.testing.datamodel.users.UserDetails;
import com.github.maximkirko.testing.services.IUserDetailsService;

@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

	@Inject
	private IUserDetailsDao userDao;

	@Override
	public UserDetails get(Long id) {
		return (UserDetails) userDao.get(id);
	}

	@Override
	public List getAll() {
		return userDao.getAll();
	}

	@Override
	public Long save(UserDetails userDetails) {

		if (userDetails.getId() == null) {
			Long id = userDao.insert(userDetails);
			return id;
		} else {
			userDao.update(userDetails);
		}
		return userDetails.getId();
	}

	@Override
	public void saveAll(List<UserDetails> usersDetails) {
		for (UserDetails userDetails : usersDetails) {
			save(userDetails);
		}

	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}
}
