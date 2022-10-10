package com.unvime.projectapi.services;

import com.unvime.projectapi.models.dto.ProjectDTO;

public interface IProjectService {
    ProjectDTO createProject(ProjectDTO dto);
    ProjectDTO updateProject(ProjectDTO dto, long id);
    void deleteProject(long id);
    ProjectDTO getProject(long id);
}
