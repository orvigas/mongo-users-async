package com.mongo.crud.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.mongo.crud.user.dto.UserDto;
import com.mongo.crud.user.exception.NotFoundException;
import com.mongo.crud.user.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	UserDto userDto;
	List<UserDto> userDtoList;

	@BeforeEach
	void setUp() {
		userDto = new UserDto();
		userDtoList = new ArrayList<>();
		userDtoList.add(userDto);
	}

	@Test
	@SuppressWarnings("unchecked")
	void testIndex() {
		when(userService.list(any(Pageable.class))).thenReturn(CompletableFuture.completedFuture(userDtoList));
		var instance = userController.index(0, 1, List.of("id"), "ASC").getResult();
		assertEquals(ArrayList.class, instance.getClass());
		assertEquals(1, ((List<UserDto>) instance).size());
	}

	@Test
	void testGet() {
		when(userService.get(any(String.class))).thenReturn(CompletableFuture.completedFuture(userDto));
		var instance = userController.get("qwerty12345").getResult();
		assertEquals(UserDto.class, instance.getClass());
		assertEquals(userDto, instance);
	}

	@Test
	void testGetNotFoundException() {
		when(userService.get(any(String.class))).thenThrow(new NotFoundException("Any error message"));
		assertThrows(NotFoundException.class, () -> {
			userController.get("qwerty12345");
		});
	}

	@Test
	void testStore() {
		when(userService.store(any(UserDto.class))).thenReturn(CompletableFuture.completedFuture(userDto));
		var instance = userController.create(userDto).getResult();
		assertEquals(UserDto.class, instance.getClass());
		assertEquals(userDto, instance);
	}

	@Test
	void testUpdate() {
		when(userService.update(anyString(), any(UserDto.class)))
				.thenReturn(CompletableFuture.completedFuture(userDto));
		var instance = userController.update("qwerty12345", userDto).getResult();
		assertEquals(UserDto.class, instance.getClass());
		assertEquals(userDto, instance);
	}

	@Test
	void testUpdateNotFoundException() {
		when(userService.update(anyString(), any(UserDto.class))).thenThrow(new NotFoundException("Any error message"));
		assertThrows(NotFoundException.class, () -> {
			userController.update("qwerty12345", userDto);
		});
	}

	@Test
	void testDelete() {
		when(userService.delete(anyString())).thenReturn(CompletableFuture.completedFuture(null));
		var instance = userController.destroy("qwerty12345").getResult();
		assertNull(instance);
	}
}
