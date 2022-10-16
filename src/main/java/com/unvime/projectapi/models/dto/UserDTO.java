package com.unvime.projectapi.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    @NotBlank(message = "El nombre de ususario es obligatorio")
    @Pattern(regexp = "[[0-9]|A-Z|a-z]{3,20}$", message = "El nombre de usuario solo puede contener letras y números, debe contener entre 3 y 20 caracteres")
    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^[[a-z]*[A-Z]+([a-z]|[A-Z])]{3,20}$",
            message = "la contraseña del usuario debe contener al menos una letra minuscula, una mayuscula, y una longitud de entre 3 a 20 caracteres")
    private String password;

}
