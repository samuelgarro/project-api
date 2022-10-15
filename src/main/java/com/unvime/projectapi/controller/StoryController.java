package com.unvime.projectapi.controller;

import com.unvime.projectapi.models.dto.StoryDTO;
import com.unvime.projectapi.models.dto.TaskDTO;
import com.unvime.projectapi.services.IStoryService;
import com.unvime.projectapi.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryController {
    @Autowired
    private IStoryService service;
    @Autowired
    private ITaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoryDTO create(@PathVariable("epicId") long epicId, @Validated @RequestBody StoryDTO dto) {
        return service.createStory(dto, epicId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoryDTO get(@PathVariable("id") long id) {
        return service.getStory(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StoryDTO> findAll() {
        return service.findAll();
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

    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getTasks(@PathVariable("id") long id) {
        return taskService.findTaskByStory(id);
    }
}
