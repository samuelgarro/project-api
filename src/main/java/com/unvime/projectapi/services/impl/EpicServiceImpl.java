package com.unvime.projectapi.services.impl;

import com.unvime.projectapi.exceptions.NotFoundException;
import com.unvime.projectapi.models.dto.EpicDTO;
import com.unvime.projectapi.models.entity.Epic;
import com.unvime.projectapi.repository.IEpicRepository;
import com.unvime.projectapi.services.IEpicService;
import com.unvime.projectapi.services.IProjectService;
import com.unvime.projectapi.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpicServiceImpl implements IEpicService {
    @Autowired
    private IEpicRepository repository;

    @Autowired
    private IProjectService projectService;

    @Override
    public EpicDTO createEpic(EpicDTO dto, long projectId) {
        Epic epic = MapperUtils.map(dto, Epic.class);

        projectService.getProject(projectId); // verifica si existe
        epic.setId(repository.count() + 1);
        epic.setProjectId(projectId);
        repository.save(epic);

        dto.setId(epic.getId());

        return dto;
    }

    @Override
    public EpicDTO updateEpic(EpicDTO dto, long id, long projectId) {
        var epic = repository.findById(id).orElseThrow(NotFoundException::new);

        if (dto.getName() != null && !dto.getName().isBlank())
            epic.setName(dto.getName());

        if (epic.getProjectId() != projectId) {
            projectService.getProject(projectId); // verifica si existe
            epic.setProjectId(projectId);
        }

        repository.save(epic);

        return MapperUtils.map(epic, EpicDTO.class);
    }

    @Override
    public void deleteEpic(long id) {
        repository.deleteById(id);
    }

    @Override
    public EpicDTO getEpic(long id) {
        var epic = repository.findById(id).orElseThrow(NotFoundException::new);
        return MapperUtils.map(epic, EpicDTO.class);
    }

    public List<EpicDTO> findEpicByProject(long id) {
        var epics = repository.findByProjectId(id);
        return MapperUtils.mapList(epics, EpicDTO.class);
    }

    @Override
    public List<EpicDTO> findAll() {
        return MapperUtils.mapList(repository.findAll(), EpicDTO.class);
    }
}
