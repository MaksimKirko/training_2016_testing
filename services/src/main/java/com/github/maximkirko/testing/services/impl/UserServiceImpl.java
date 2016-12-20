package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IUserDao;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.services.IGradeService;
import com.github.maximkirko.testing.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Inject
	private IUserDao userDao;

	@Inject
	private IGradeService gradeService;

	@Override
	public User get(Long id) {
		return userDao.get(id);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public User getWithRole(Long id) {
		return userDao.getWithRole(id);
	}

	@Override
	public List<User> getByRole(Role role) {
		return userDao.getByRole(role);
	}

	@Override
	public User getWithGrades(Long id) {

		User student = userDao.getWithGrades(id);
		return student;
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public Long save(User user) {

		if (user == null) {
			return null;
		}

		if (user.getId() == null) {
			Long id = userDao.insert(user);
			user.setId(id);
		} else {
			userDao.update(user);
		}
		return user.getId();
	}

	@Override
	public List<Long> saveAll(List<User> users) {

		List<Long> idList = new ArrayList<Long>();

		for (User user : users) {
			Long id = save(user);
			user.setId(id);
			idList.add(id);
		}
		return idList;

	}

	@Override
	public void delete(Long id) {

		User user = getWithGrades(id);
		if(user == null) {
			return;
		}

		List<Grade> grades = user.getGrades();
		if (grades != null) {
			for (Grade grade : grades) {
				gradeService.delete(grade.getId());
			}
		}
		userDao.delete(id);
	}

}
