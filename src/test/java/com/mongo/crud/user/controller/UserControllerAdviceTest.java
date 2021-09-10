package com.mongo.crud.user.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.mongo.crud.user.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class UserControllerAdviceTest {

	UserControllerAdvice userControllerAdvice;

	static final String ERROR_MESSAGE = "Any error message";
	WebExchangeBindException webExchangeBindException;

	@BeforeEach
	void setUp() {
		userControllerAdvice = new UserControllerAdvice();
		webExchangeBindException = mock(WebExchangeBindException.class);
	}

	@Test
	void testNotFoundException() {
		var instance = userControllerAdvice.notFoundException(new NotFoundException(ERROR_MESSAGE));
		assertNotNull(instance);

	}

	@Test
	void HttpRequestMethodNotSupportedException() {
		var instance = userControllerAdvice
				.httpRequestMethodNotSupportedException(new HttpRequestMethodNotSupportedException("GETS"));
		assertNotNull(instance);
	}

	@Test
	void testHttpMessageNotReadableException() {
		var instance = userControllerAdvice
				.httpMessageNotReadableException(new HttpMessageNotReadableException(ERROR_MESSAGE, null, null));
		assertNotNull(instance);
	}

	@Test
	void TestMethodArgumentNotValidException() {
		var instance = userControllerAdvice
				.methodArgumentNotValidException(new MethodArgumentNotValidException(null, webExchangeBindException));
		assertNotNull(instance);
	}
}
