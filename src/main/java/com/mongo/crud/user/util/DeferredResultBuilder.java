package com.mongo.crud.user.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.springframework.web.context.request.async.DeferredResult;

public class DeferredResultBuilder {

	private DeferredResultBuilder() {
	}

	public static <T> DeferredResult<T> from(final CompletableFuture<T> future) {
		final var deferred = new DeferredResult<T>();
		future.thenAccept(deferred::setResult);
		future.exceptionally(ex -> {
			if (ex instanceof CompletionException) {
				deferred.setErrorResult(ex.getCause());
			} else {
				deferred.setErrorResult(ex);
			}
			return null;
		});
		return deferred;
	}
}
