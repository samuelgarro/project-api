package com.unvime.projectapi.services.impl;

import com.unvime.projectapi.config.JWTAuthorization;
import com.unvime.projectapi.exceptions.LoginException;
import com.unvime.projectapi.exceptions.NotFoundException;
import com.unvime.projectapi.models.dto.TokenDTO;
import com.unvime.projectapi.models.dto.UserDTO;
import com.unvime.projectapi.models.entity.User;
import com.unvime.projectapi.repository.IUserRepository;
import com.unvime.projectapi.services.IUserService;
import com.unvime.projectapi.utils.MapperUtils;
import com.unvime.projectapi.utils.Roles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;

    @Override
    public TokenDTO login(UserDTO dto) {
        User user = repository.findByUsername(dto.getUsername());
        if (user == null) throw new LoginException();
        if (!user.getPassword().equals(dto.getPassword())) throw new LoginException();

        return new TokenDTO(getToken(user));
    }

    private String getToken(User user) {
        Roles rol = user.getRol();
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(rol.name());
        var listAuthority = grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .claim("authorities", listAuthority)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // en 10 minutos
                .signWith(SignatureAlgorithm.HS512, JWTAuthorization.SECRET.getBytes())
                .compact();

        return String.format("Bearer %s", token);

    }

    @Override
    public UserDTO get(long id) {
        var user =  repository.findById(id).orElseThrow(NotFoundException::new);
        var dto = MapperUtils.map(user, UserDTO.class);
        dto.setPassword(null);
        return dto;
    }

    @Override
    public List<UserDTO> findAll() {
        var users =  repository.findAll();
        var result = MapperUtils.mapList(users, UserDTO.class);
        result.forEach(dto -> dto.setPassword(null));
        return result;
    }
}
