package com.unvime.projectapi.services.impl;

import com.unvime.projectapi.exceptions.NotFoundException;
import com.unvime.projectapi.models.dto.StoryDTO;
import com.unvime.projectapi.models.entity.Story;
import com.unvime.projectapi.repository.IStoryRepository;
import com.unvime.projectapi.services.IEpicService;
import com.unvime.projectapi.services.IStoryService;
import com.unvime.projectapi.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryServiceImpl implements IStoryService {
    @Autowired
    private IStoryRepository repository;

    @Autowired
    private IEpicService epicService;

    @Override
    public StoryDTO createStory(StoryDTO dto, long epicId) {
        Story story = MapperUtils.map(dto, Story.class);

        epicService.getEpic(epicId); // verifica si existe
        story.setId(repository.count() + 1);
        story.setEpicId(epicId);
        repository.save(story);

        dto.setId(story.getId());
        return dto;
    }

    @Override
    public StoryDTO updateStory(StoryDTO dto, long id, long epicId) {
        var story = repository.findById(id).orElseThrow(NotFoundException::new);

        if (dto.getName() != null && !dto.getName().isBlank())
            story.setName(dto.getName());

        if (dto.getDescription() != null && !dto.getDescription().isBlank())
            story.setDescription(dto.getDescription());

        if (story.getEpicId() != epicId) {
            epicService.getEpic(epicId); // verifica si existe
            story.setEpicId(epicId);
        }

        repository.save(story);

        return MapperUtils.map(story, StoryDTO.class);
    }

    @Override
    public void deleteStory(long id) {
        repository.deleteById(id);
    }

    @Override
    public StoryDTO getStory(long id) {
        var story = repository.findById(id).orElseThrow(NotFoundException::new);
        return MapperUtils.map(story, StoryDTO.class);
    }

    public List<StoryDTO> findStoryByEpic(long id) {
        var stories = repository.findByEpicId(id);
        return MapperUtils.mapList(stories, StoryDTO.class);
    }
}
