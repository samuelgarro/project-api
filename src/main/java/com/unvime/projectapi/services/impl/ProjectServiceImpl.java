package com.unvime.projectapi.services.impl;

import com.unvime.projectapi.exceptions.NotFoundException;
import com.unvime.projectapi.models.dto.ProjectDTO;
import com.unvime.projectapi.models.entity.Project;
import com.unvime.projectapi.repository.IProjectRepository;
import com.unvime.projectapi.services.IProjectService;
import com.unvime.projectapi.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private IProjectRepository repository;

    @Override
    public ProjectDTO createProject(ProjectDTO dto) {
        Project project = MapperUtils.map(dto, Project.class);

        project.setId(repository.count() + 1);
        repository.save(project);

        dto.setId(project.getId());

        return dto;
    }

    @Override
    public ProjectDTO updateProject(ProjectDTO dto, long id) {
        var project = repository.findById(id).orElseThrow(NotFoundException::new);

        if (dto.getName() != null && !dto.getName().isBlank())
            project.setName(dto.getName());

        repository.save(project);

        return MapperUtils.map(project, ProjectDTO.class);
    }

    @Override
    public void deleteProject(long id) {
        repository.deleteById(id);
    }

    @Override
    public ProjectDTO getProject(long id) {
        Project project = repository.findById(id).orElseThrow(NotFoundException::new);
        return MapperUtils.map(project, ProjectDTO.class);
    }
}
