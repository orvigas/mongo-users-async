package com.mongo.crud.user.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

	private int code;
	private HttpStatus status;
	private String msg;

}
