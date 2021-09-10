package com.mongo.crud.user.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.async.DeferredResult;

import com.mongo.crud.user.exception.NotFoundException;

class DeferredResultBuilderTest {

	@Test
	void testFrom() {
		var result = DeferredResultBuilder.from(CompletableFuture.completedFuture(1));
		assertEquals(DeferredResult.class, result.getClass());
	}

	@Test
	void testFromException() {
		var result = DeferredResultBuilder
				.from(CompletableFuture.failedFuture(new CompletionException(new NotFoundException())));
		assertEquals(DeferredResult.class, result.getClass());

		result = DeferredResultBuilder.from(CompletableFuture.failedFuture(new NotFoundException()));
		assertEquals(DeferredResult.class, result.getClass());
	}

}
