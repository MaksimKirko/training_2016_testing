package com.github.maximkirko.testing.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;
import com.github.maximkirko.testing.services.IGenericService;
import com.github.maximkirko.testing.web.model.WebModel;

@RestController
public abstract class GenericController<Entity extends AbstractModel, Model extends WebModel> {

	@Inject
	protected IGenericService<Entity, Long> service;

	@Inject
	protected ConversionService conversionService;

	protected Class<Entity> entityClass;
	protected Class<Model> modelClass;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Model>> getAll(@RequestHeader(value = "Authorization") String authHeader) {

		List<Entity> all = service.getAll();

		if (all == null) {
			return new ResponseEntity<List<Model>>(HttpStatus.NOT_FOUND);
		}

		List<Model> converted = new ArrayList<>();
		for (Entity entity : all) {
			converted.add(conversionService.convert(entity, modelClass));
		}

		return new ResponseEntity<List<Model>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
	public ResponseEntity<Model> getById(@PathVariable Long entityId,
			@RequestHeader(value = "Authorization") String authHeader) {

		Entity entity = service.get(entityId);
		if (entity == null) {
			return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Model>(conversionService.convert(entity, modelClass), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewEntity(@RequestBody Model entityModel) {

		service.save(conversionService.convert(entityModel, entityClass));
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{entityId}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateEntity(@RequestBody Model entityModel, @PathVariable Long entityId) {

		Entity entity = conversionService.convert(entityModel, entityClass);
		entity.setId(entityId);
		service.save(entity);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{entityId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long entityId) {

		service.delete(entityId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
