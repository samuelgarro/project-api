package com.unvime.projectapi.services.impl;

import com.unvime.projectapi.exceptions.NotFoundException;
import com.unvime.projectapi.models.dto.TaskDTO;
import com.unvime.projectapi.models.entity.Task;
import com.unvime.projectapi.repository.ITaskRepository;
import com.unvime.projectapi.services.IStoryService;
import com.unvime.projectapi.services.ITaskService;
import com.unvime.projectapi.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private ITaskRepository repository;

    @Autowired
    private IStoryService storyService;

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        Task task = MapperUtils.map(dto, Task.class);
        long storyId = dto.getStory().getId();

        storyService.getStory(storyId); // verifica si existe
        task.setId(repository.count() + 1);
        task.setStoryId(storyId);
        repository.save(task);

        dto.setId(task.getId());

        return dto;
    }

    @Override
    public TaskDTO updateTask(TaskDTO dto, long id) {
        var task = repository.findById(id).orElseThrow(NotFoundException::new);
        long storyId = dto.getStory().getId();

        task.setName(dto.getName());

        if (task.getStoryId() != storyId) {
            storyService.getStory(storyId); // verifica si existe
            task.setStoryId(storyId);
        }

        repository.save(task);

        return MapperUtils.map(task, TaskDTO.class);
    }

    @Override
    public void deleteTask(long id) {
        repository.deleteById(id);
    }

    @Override
    public TaskDTO getTask(long id) {
        var task = repository.findById(id).orElseThrow(NotFoundException::new);
        return MapperUtils.map(task, TaskDTO.class);
    }

    public List<TaskDTO> findTaskByStory(long id) {
        var tasks = repository.findByStoryId(id);
        return MapperUtils.mapList(tasks, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> findAll() {
        return MapperUtils.mapList(repository.findAll(), TaskDTO.class);
    }
}
