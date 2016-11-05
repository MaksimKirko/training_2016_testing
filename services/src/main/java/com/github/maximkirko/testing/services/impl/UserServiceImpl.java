package com.github.maximkirko.testing.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daodb.IUserDao;
import com.github.maximkirko.testing.datamodel.users.User;
import com.github.maximkirko.testing.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Inject
	private IUserDao userDao;

	@Override
	public User get(Long id) {
		return (User) userDao.get(id);
	}

	@Override
	public List getAll() {
		return userDao.getAll();
	}

	@Override
	public Long save(User user) {

		if (user.getId() == null) {
			Long id = userDao.insert(user);
			return id;
		} else {
			userDao.update(user);
		}
		return user.getId();
	}

	@Override
	public void saveAll(List<User> users) {
		for (User user : users) {
			save(user);
		}

	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

}
