package com.unvime.projectapi.repository;

import com.unvime.projectapi.models.dto.StoryDTO;
import com.unvime.projectapi.models.entity.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface IStoryRepository extends MongoRepository<Story, Long> {
    List<StoryDTO> findByEpicId(long epicId);
}
