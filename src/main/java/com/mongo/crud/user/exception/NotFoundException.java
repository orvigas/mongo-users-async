package com.mongo.crud.user.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6664146218673629628L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(final Throwable error) {
		super(error);
	}

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final String message, final Throwable error) {
		super(message, error);
	}
}