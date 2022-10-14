package com.unvime.projectapi.services;

import com.unvime.projectapi.models.dto.EpicDTO;

import java.util.List;

public interface IEpicService {
    EpicDTO createEpic(EpicDTO dto, long projectId);
    EpicDTO updateEpic(EpicDTO dto, long id, long projectId);
    void deleteEpic(long id);
    EpicDTO getEpic(long id);
    List<EpicDTO> findEpicByProject(long id);
    List<EpicDTO> findAll();
}
