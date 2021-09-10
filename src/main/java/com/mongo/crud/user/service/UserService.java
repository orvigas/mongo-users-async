package com.mongo.crud.user.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mongo.crud.user.dto.UserDto;
import com.mongo.crud.user.exception.NotFoundException;
import com.mongo.crud.user.model.User;
import com.mongo.crud.user.repository.UserRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service
public class UserService {

	private final UserRepository userRepository;

	protected static final String NOT_FOUND_MESSAGE = "No records found for id: ";

	@Async
	public CompletableFuture<List<UserDto>> list(final Pageable pagination) {
		return CompletableFuture.completedFuture(userRepository.retrieveAllQuotesPaged(pagination).stream()
				.map(UserDto::from).collect(Collectors.toList()));
	}

	@Async
	public CompletableFuture<UserDto> get(final String id) {
		return CompletableFuture.completedFuture(userRepository.findById(id).map(UserDto::from)
				.orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE + id)));
	}

	@Async
	public CompletableFuture<UserDto> store(final UserDto usuario) {
		log.info("Saving the user: {}", usuario);
		return CompletableFuture.completedFuture(UserDto.from(userRepository.save(User.from(usuario))));
	}

	@Async
	public CompletableFuture<UserDto> update(final String id, final UserDto usuario) {
		log.info("updating the user identified with id: {}", id);
		return CompletableFuture.completedFuture(userRepository.findById(id).map(dbInstance -> {
			BeanUtils.copyProperties(usuario, dbInstance, "id");
			return userRepository.save(dbInstance);
		}).map(UserDto::from).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE + id)));
	}

	@Async
	public CompletableFuture<Void> delete(final String id) {
		log.info("Deleting the user identified with id: {}", id);
		return CompletableFuture.runAsync(() -> userRepository.deleteById(id));
	}

}
