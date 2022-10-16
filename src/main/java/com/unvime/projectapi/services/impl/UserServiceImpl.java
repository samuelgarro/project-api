package com.unvime.projectapi.services.impl;

import com.unvime.projectapi.config.JWTAuthorization;
import com.unvime.projectapi.exceptions.LoginException;
import com.unvime.projectapi.models.dto.TokenDTO;
import com.unvime.projectapi.models.dto.UserDTO;
import com.unvime.projectapi.models.entity.User;
import com.unvime.projectapi.repository.IUserRepository;
import com.unvime.projectapi.services.IUserService;
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
        //User user = repository.findByUsername(dto.getUsername());
        User user = new User();
        user.setId(1L);
        user.setUsername("samuel");
        user.setPassword("Pepe");
        user.setRol(Roles.ROLE_ADMIN);

        // si no existe el username
        if (user == null) throw new LoginException();
        // si la contraseña es erronea
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
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }
}
