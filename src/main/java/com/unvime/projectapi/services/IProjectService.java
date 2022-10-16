package com.unvime.projectapi.services;

import com.unvime.projectapi.models.dto.EpicDTO;
import com.unvime.projectapi.models.dto.ProjectDTO;

import java.util.List;

public interface IProjectService {
    ProjectDTO createProject(ProjectDTO dto);
    ProjectDTO updateProject(ProjectDTO dto, long id);
    void deleteProject(long id);
    ProjectDTO getProject(long id);
    List<ProjectDTO> findAll();
}
