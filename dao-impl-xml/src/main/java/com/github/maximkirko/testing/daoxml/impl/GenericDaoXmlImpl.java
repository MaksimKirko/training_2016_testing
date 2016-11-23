package com.github.maximkirko.testing.daoxml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IGenericDao;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.FilenameAware;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;
import com.thoughtworks.xstream.XStream;

@Repository
public abstract class GenericDaoXmlImpl<T extends AbstractModel, PK extends Serializable>
		implements IGenericDao<T, PK> {

	protected Class<T> entityClass;

	protected XStream xstream;

	@Value("${basePath}")
	protected String basePath;

	protected String filename;
	
	protected File file;

	public GenericDaoXmlImpl(Class<T> entityClass) throws IOException {
		
		this.entityClass = entityClass;
		filename = FilenameAware.getFilenameByClass(entityClass);
	}
	
	@PostConstruct
    private void intialize() throws IOException {
		
		xstream = new XStream();
		xstream.alias(entityClass.getSimpleName(), entityClass);

		file = new File(basePath + filename);

		if (!file.exists()) {
			file.createNewFile();
			xstream.toXML(new ArrayList<>(), new FileOutputStream(file));
		}
    }

	@Override
	public T get(PK id) {

		List<T> entities = readCollection();

		for (T entity : entities) {
			if (entity.getId().equals(id)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK insert(T entity) {

		List<T> entities = readCollection();
		Long id = getNextId(entities);

		entities.add(entity);

		entity.setId(id);

		writeCollection(entities);
		return (PK) id;
	}

	@Override
	public void update(T newEntity) {

		List<T> entities = readCollection();

		for (T entity : entities) {

			if (!entity.getId().equals(newEntity.getId())) {

				entity = newEntity;
				return;
			}
		}

		writeCollection(entities);
	}

	@Override
	public void delete(PK id) {

		List<T> entities = readCollection();

		int i = 0;
		for (T entity : entities) {

			if (entity.getId().equals(id)) {

				entities.remove(i);
				break;
			}
			i++;
		}

		writeCollection(entities);
	}

	@Override
	public List<T> getAll() {

		return readCollection();
	}

	@SuppressWarnings("unchecked")
	private List<T> readCollection() {
		return (List<T>) xstream.fromXML(file);
	}

	private void writeCollection(List<T> newList) {

		try {

			xstream.toXML(newList, new FileOutputStream(file));

		} catch (FileNotFoundException e) {

			throw new RuntimeException(e);// TODO custom exception
		}
	}

	private long getNextId(List<T> entities) {

		return entities.isEmpty() ? 1l : entities.get(entities.size() - 1).getId() + 1;
	}

}
