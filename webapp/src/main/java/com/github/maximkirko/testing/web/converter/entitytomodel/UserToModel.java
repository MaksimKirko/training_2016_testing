package com.github.maximkirko.testing.web.converter.entitytomodel;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.web.model.RoleModel;
import com.github.maximkirko.testing.web.model.UserModel;

public class UserToModel implements Converter<User, UserModel> {

	private RoleToModel roleToModel = new RoleToModel();

	@Override
	public UserModel convert(User user) {

		UserModel model = new UserModel();
		model.setId(user.getId());
		model.setFirstName(user.getFirstName());
		model.setLastName(user.getLastName());
		model.setAge(user.getAge());
		model.setCourse(user.getCourse());
		model.setEmail(user.getEmail());
		model.setPassword(user.getPassword());
		RoleModel role = roleToModel.convert(user.getRole());
		model.setRole(role);
		return model;
	}

}
