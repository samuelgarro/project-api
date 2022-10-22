package com.unvime.projectapi.services;

import com.unvime.projectapi.models.dto.TokenDTO;
import com.unvime.projectapi.models.dto.UserDTO;

import java.util.List;

public interface IUserService {
    TokenDTO login(UserDTO dto);
    UserDTO get(long id);
    List<UserDTO> findAll();
}
