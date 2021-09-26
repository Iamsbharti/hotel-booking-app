package com.hotel.management.repository;

import com.hotel.management.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    List<User> findAll();
    User save(User user);
    @Query("{ 'userId' : ?0 }")
    Optional<User> findByTheUserId(String userId);
}
