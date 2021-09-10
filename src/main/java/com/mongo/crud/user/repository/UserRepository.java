package com.mongo.crud.user.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongo.crud.user.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{ id: { $exists: true }}")
	List<User> retrieveAllQuotesPaged(final Pageable pagination);

}
