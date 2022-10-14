package com.unvime.projectapi.services;

import com.unvime.projectapi.models.dto.EpicDTO;
import com.unvime.projectapi.models.dto.StoryDTO;

import java.util.List;

public interface IStoryService {
    StoryDTO createStory(StoryDTO dto, long epicId);
    StoryDTO updateStory(StoryDTO dto, long id, long epicId);
    void deleteStory(long id);
    StoryDTO getStory(long id);
    List<StoryDTO> findStoryByEpic(long id);
    List<StoryDTO> findAll();
}
