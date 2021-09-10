package com.mongo.crud.user.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongo.crud.user.dto.UserDto;

@ExtendWith(MockitoExtension.class)
class UsuarioTest {

	@Test
	void testFrom() {
		var instance = User.from(new UserDto());
		assertNotNull(instance);
	}

	@Test
	void testFromNull() {
		var instance = User.from(null);
		assertNull(instance);
	}

}
