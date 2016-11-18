package com.github.maximkirko.testing.daoxml.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.maximkirko.testing.daoapi.IGenericDao;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;
import com.thoughtworks.xstream.XStream;

public abstract class GenericDaoXmlImpl<T extends AbstractModel, PK extends Serializable> implements IGenericDao<T, PK> {

	protected XStream xstream;
	
	protected File file;

	protected String filePath;
	
	
	
	public GenericDaoXmlImpl() {
//		xstream = new XStream();
//        xstream.alias("book", Book.class);
//
//        file = new File(basePath + "/books.xml");
//        if (!file.exists()) {
//            file.createNewFile();
//            xstream.toXML(new ArrayList<>(), new FileOutputStream(
//                    file));
//        }
	}

	@Override
	public abstract Map<String, Object> entityToMap(T entity);

	@Override
	public T get(PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PK insert(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PK id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
