package com.github.maximkirko.testing.web.converter.entitytomodel;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.web.model.SubjectModel;

public class SubjectToModel implements Converter<Subject, SubjectModel> {

	@Override
	public SubjectModel convert(Subject subject) {

		SubjectModel model = new SubjectModel();
		model.setId(subject.getId());
		model.setTitle(subject.getTitle());
		model.setDescription(subject.getDescription());
		return model;
	}

}
