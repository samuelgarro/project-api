package com.unvime.projectapi.services;

import com.unvime.projectapi.models.dto.EpicDTO;
import com.unvime.projectapi.models.dto.TaskDTO;

import java.util.List;

public interface ITaskService {
    TaskDTO createTask(TaskDTO dto, long storyId);
    TaskDTO updateTask(TaskDTO dto, long id, long storyId);
    void deleteTask(long id);
    TaskDTO getTask(long id);
    List<TaskDTO> findTaskByStory(long id);
    List<TaskDTO> findAll();
}
