package com.unvime.projectapi.repository;

import com.unvime.projectapi.models.entity.Epic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface IEpicRepository extends MongoRepository<Epic, Long>  {
    List<Epic> findByProjectId(long projectId);
}
