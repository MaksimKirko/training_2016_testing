package com.github.maximkirko.testing.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.daoapi.IRoleDao;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.services.IRoleService;
import com.github.maximkirko.testing.services.IUserService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Inject
	private IRoleDao roleDao;

	@Inject
	private IUserService userService;

	@Override
	public Role get(Long id) {

		return roleDao.get(id);
	}

	@Override
	public Role getByType(RoleEnum type) {

		return roleDao.getByType(type);
	}

	@Override
	public List<Role> getAll() {

		return roleDao.getAll();
	}

	@Override
	public Long save(Role entity) {

		if (entity.getId() == null) {

			Long id = roleDao.insert(entity);
			entity.setId(id);
		} else {

			roleDao.update(entity);
		}

		return entity.getId();
	}

	@Override
	public List<Long> saveAll(List<Role> entities) {

		List<Long> idList = new ArrayList<Long>();

		for (Role role : entities) {
			Long id = save(role);
			role.setId(id);
			idList.add(id);
		}

		return idList;

	}

	@Override
	public void delete(Long id) {

		Role role = get(id);
		List<User> users = userService.getByRole(role);
		for (User user : users) {
			userService.delete(user.getId());
		}

		roleDao.delete(id);

	}

}
