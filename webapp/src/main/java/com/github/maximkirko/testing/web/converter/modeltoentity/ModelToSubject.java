package com.github.maximkirko.testing.web.converter.modeltoentity;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.web.model.SubjectModel;

public class ModelToSubject implements Converter<SubjectModel, Subject> {

	@Override
	public Subject convert(SubjectModel model) {

		Subject subject = new Subject();
		subject.setId(model.getId());
		subject.setTitle(model.getTitle());
		subject.setDescription(model.getDescription());
		return subject;
	}

}
