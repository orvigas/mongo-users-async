package com.mongo.crud.user.model;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongo.crud.user.dto.UserDto;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	public static User from(final UserDto source) {

		if (source == null) {
			return null;
		}

		final var instance = new User();
		BeanUtils.copyProperties(source, instance);
		return instance;
	}

}
