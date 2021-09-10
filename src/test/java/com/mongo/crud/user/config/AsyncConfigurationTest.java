package com.mongo.crud.user.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class AsyncConfigurationTest {

	/*
	 * I setup a context runner with the class ExampleConfiguration in it. For that,
	 * I use ApplicationContextRunner#withUserConfiguration() methods to populate
	 * the context.
	 */
	ApplicationContextRunner context = new ApplicationContextRunner().withUserConfiguration(AsyncConfiguration.class);

	@Test
	void testAsyncConfiguration() {
		/*
		 * We start the context and we will be able to trigger assertions in a lambda
		 * receiving a AssertableApplicationContext
		 */
		context.run(it -> {
			/*
			 * I can use assertThat to assert on the context and check if the @Bean
			 * configured is present (and unique)
			 */
			assertThat(it).hasSingleBean(AsyncConfiguration.class);
		});
	}

}
