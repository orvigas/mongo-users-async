package com.mongo.crud.user.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongo.crud.user.exception.ExceptionResponse;
import com.mongo.crud.user.exception.NotFoundException;

@ControllerAdvice
public class UserControllerAdvice {

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> notFoundException(final NotFoundException xcp) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<ExceptionResponse> httpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException xcp) {
		return ResponseEntity.badRequest().body(ExceptionResponse.builder().code(HttpStatus.METHOD_NOT_ALLOWED.value())
				.status(HttpStatus.METHOD_NOT_ALLOWED).msg(xcp.getLocalizedMessage()).build());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<ExceptionResponse> httpMessageNotReadableException(
			final HttpMessageNotReadableException xcp) {
		return ResponseEntity.badRequest().body(ExceptionResponse.builder().code(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST).msg(xcp.getLocalizedMessage()).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ExceptionResponse> methodArgumentNotValidException(
			final MethodArgumentNotValidException xcp) {
		return ResponseEntity.badRequest().body(ExceptionResponse.builder().code(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST)
				.msg(String.join(", ",
						xcp.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())))
				.build());
	}

}
