package com.unvime.projectapi.controller;

import com.unvime.projectapi.models.dto.TaskDTO;
import com.unvime.projectapi.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/{projectId}/epic/{epicId}/story/{storyId}/task")
public class TaskController {
    @Autowired
    private ITaskService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@PathVariable("storyId") long storyId, @RequestBody TaskDTO dto) {
        return service.createTask(dto, storyId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO get(@PathVariable("id") long id) {
        return service.getTask(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO update(@PathVariable("storyId") long storyId, @PathVariable("id") long id, @RequestBody TaskDTO dto) {
        return service.updateTask(dto, id, storyId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.deleteTask(id);
    }

}
