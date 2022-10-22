package com.unvime.projectapi.controller;

import com.unvime.projectapi.models.dto.TokenDTO;
import com.unvime.projectapi.models.dto.UserDTO;
import com.unvime.projectapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserService service;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO login(@Validated @RequestBody UserDTO dto) {
        return service.login(dto);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO get(@PathVariable long id) {
        return service.get(id);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAll() {
        return service.findAll();
    }

}
