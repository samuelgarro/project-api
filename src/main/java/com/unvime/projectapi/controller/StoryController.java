package com.unvime.projectapi.controller;

import com.unvime.projectapi.models.dto.StoryDTO;
import com.unvime.projectapi.services.IStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/{projectId}/epic/{epicId}/story")
public class StoryController {
    @Autowired
    private IStoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoryDTO create(@PathVariable("epicId") long epicId, @RequestBody StoryDTO dto) {
        return service.createStory(dto, epicId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoryDTO get(@PathVariable("id") long id) {
        return service.getStory(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoryDTO update(@PathVariable("epicId") long epicId, @PathVariable("id") long id, @RequestBody StoryDTO dto) {
        return service.updateStory(dto, id, epicId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.deleteStory(id);
    }

}
