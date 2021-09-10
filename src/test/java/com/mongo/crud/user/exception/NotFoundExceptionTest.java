package com.mongo.crud.user.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.naming.directory.InvalidAttributesException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotFoundExceptionTest {

	@Test
	void testNotFoundException() {
		var exception = new NotFoundException();
		assertEquals(NotFoundException.class, exception.getClass());
	}

	@Test
	void NotFoundExceptionWithThrowable() {
		var exception = new NotFoundException(new InvalidAttributesException());
		assertEquals(NotFoundException.class, exception.getClass());
	}

	@Test
	void NotFoundExceptionWithMessage() {
		var exception = new NotFoundException("Any error message");
		assertEquals(NotFoundException.class, exception.getClass());
	}

	@Test
	void NotFoundExceptionWithMessageAndThrowable() {
		var exception = new NotFoundException("Any error message", new InvalidAttributesException());
		assertEquals(NotFoundException.class, exception.getClass());
	}

}
