package com.unvime.projectapi.repository;

import com.unvime.projectapi.models.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITaskRepository extends MongoRepository<Task, Long> {
    List<Task> findByStoryId(long storyId);
}
