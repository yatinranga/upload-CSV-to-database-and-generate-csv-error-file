package com.digilytics.assignment.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.digilytics.assignment.entity.view.UserRequest;

public class UserFileRowMapper implements FieldSetMapper<UserRequest> {

	@Override
	public UserRequest mapFieldSet(FieldSet fieldSet) throws BindException {
		UserRequest userRequest = new UserRequest();

		userRequest.setEmail(fieldSet.readString("email"));
		userRequest.setName(fieldSet.readString("name"));
		userRequest.setRoles(fieldSet.readString("roles"));
		return userRequest;
	}

}
