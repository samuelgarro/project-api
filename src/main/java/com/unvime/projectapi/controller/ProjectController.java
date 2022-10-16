package com.unvime.projectapi.controller;

import com.unvime.projectapi.models.dto.EpicDTO;
import com.unvime.projectapi.models.dto.ProjectDTO;
import com.unvime.projectapi.services.IEpicService;
import com.unvime.projectapi.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private IProjectService service;
    @Autowired
    private IEpicService epicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO create(@Validated @RequestBody ProjectDTO dto) {
        return service.createProject(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO get(@PathVariable long id) {
        return service.getProject(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDTO> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO update(@PathVariable long id, @Validated @RequestBody ProjectDTO dto) {
        return service.updateProject(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteProject(id);
    }

    @GetMapping("/{id}/epics")
    @ResponseStatus(HttpStatus.OK)
    public List<EpicDTO> getEpics(@PathVariable long id) {
        return epicService.findEpicByProject(id);
    }
}
