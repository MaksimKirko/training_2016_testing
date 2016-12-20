package com.github.maximkirko.testing.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.services.IAuthenticationService;
import com.github.maximkirko.testing.services.IUserService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Inject
	private IUserService userService;

	@Override
	public boolean validateUserPassword(String username, String password) {

		User user = userService.getByEmail(username);

		if (username.equals(user.getEmail()) && password.equals(user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean validateUserRole(String username) {

		User user = userService.getByEmail(username);

		if (user.getRole().getType().equals(RoleEnum.TUTOR)) {
			return true;
		}
		return false;
	}

}
