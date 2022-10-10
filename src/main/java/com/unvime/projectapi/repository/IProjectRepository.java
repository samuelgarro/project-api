package com.unvime.projectapi.repository;

import com.unvime.projectapi.models.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProjectRepository extends MongoRepository<Project, Long> {
}
