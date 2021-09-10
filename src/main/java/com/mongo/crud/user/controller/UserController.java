package com.mongo.crud.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.mongo.crud.user.dto.UserDto;
import com.mongo.crud.user.service.UserService;
import com.mongo.crud.user.util.DeferredResultBuilder;

import lombok.Data;

@Data
@Validated
@RestController
@RequestMapping("/usuario")
public class UserController {

	private final UserService userService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public DeferredResult<List<UserDto>> index(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) List<String> sortBy,
			@RequestParam(defaultValue = "ASC", required = false) String sort) {
		final Pageable pagination = PageRequest.of(page, size,
				Sort.by(Direction.valueOf(sort.toUpperCase()), sortBy.toArray(new String[sortBy.size()])));
		return DeferredResultBuilder.from(userService.list(pagination));
	}

	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public DeferredResult<UserDto> get(@PathVariable(name = "id", required = true) final String id) {
		return DeferredResultBuilder.from(userService.get(id));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public DeferredResult<UserDto> create(@RequestBody @NonNull @Valid UserDto usuario) {
		return DeferredResultBuilder.from(userService.store(usuario));
	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public DeferredResult<UserDto> update(@PathVariable(name = "id", required = true) final String id,
			@RequestBody @NonNull @Valid final UserDto usuario) {
		return DeferredResultBuilder.from(userService.update(id, usuario));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public DeferredResult<Void> destroy(@PathVariable(name = "id", required = true) final String id) {
		return DeferredResultBuilder.from(userService.delete(id));
	}

}
