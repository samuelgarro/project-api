package com.unvime.projectapi.repository;

import com.unvime.projectapi.models.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
}
