package com.unvime.projectapi.controller;

import com.unvime.projectapi.models.dto.EpicDTO;
import com.unvime.projectapi.models.dto.StoryDTO;
import com.unvime.projectapi.services.IEpicService;
import com.unvime.projectapi.services.IStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epics")
public class EpicController {
    @Autowired
    private IEpicService service;
    @Autowired
    private IStoryService storyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EpicDTO create(@Validated @RequestBody EpicDTO dto) {
        return service.createEpic(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EpicDTO get(@PathVariable("id") long id) {
        return service.getEpic(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EpicDTO> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EpicDTO update(@PathVariable("id") long id, @Validated @RequestBody EpicDTO dto) {
        return service.updateEpic(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.deleteEpic(id);
    }

    @GetMapping("/{id}/stories")
    @ResponseStatus(HttpStatus.OK)
    public List<StoryDTO> getStories(@PathVariable("id") long id) {
        return storyService.findStoryByEpic(id);
    }
}
