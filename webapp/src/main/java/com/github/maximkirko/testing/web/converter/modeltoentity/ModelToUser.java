package com.github.maximkirko.testing.web.converter.modeltoentity;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;
import com.github.maximkirko.testing.web.model.UserModel;

public class ModelToUser implements Converter<UserModel, User> {

	private ModelToRole modelToRole = new ModelToRole();

	@Override
	public User convert(UserModel model) {

		User user = new User();
		user.setId(model.getId());
		user.setFirstName(model.getFirstName());
		user.setLastName(model.getLastName());
		user.setAge(model.getAge());
		user.setCourse(model.getCourse());
		user.setEmail(model.getEmail());
		user.setPassword(model.getPassword());
		Role role = modelToRole.convert(model.getRole());
		user.setRole(role);
		return user;
	}

}
