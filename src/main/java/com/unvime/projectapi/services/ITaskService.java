package com.unvime.projectapi.services;

import com.unvime.projectapi.models.dto.TaskDTO;

import java.util.List;

public interface ITaskService {
    TaskDTO createTask(TaskDTO dto);
    TaskDTO updateTask(TaskDTO dto, long id);
    void deleteTask(long id);
    TaskDTO getTask(long id);
    List<TaskDTO> findTaskByStory(long id);
    List<TaskDTO> findAll();
}
