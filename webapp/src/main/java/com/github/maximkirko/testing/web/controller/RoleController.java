package com.github.maximkirko.testing.web.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;
import com.github.maximkirko.testing.services.IRoleService;
import com.github.maximkirko.testing.web.model.RoleModel;

@RestController
@RequestMapping("/admin/roles")
public class RoleController extends GenericController<Role, RoleModel> {

	@Inject
	private IRoleService roleService;

	public RoleController() {

		super.service = roleService;
		super.entityClass = Role.class;
		super.modelClass = RoleModel.class;
	}

	@RequestMapping(value = "/byType/{entityType}", method = RequestMethod.GET)
	public ResponseEntity<RoleModel> getBySubjectId(@PathVariable RoleEnum entityType) {

		Role role = roleService.getByType(entityType);

		if (role == null) {
			return new ResponseEntity<RoleModel>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RoleModel>(conversionService.convert(role, modelClass), HttpStatus.OK);
	}

}
