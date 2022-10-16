package com.unvime.projectapi.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDTO {
    private Long id;
    @NotBlank(message = "Debe contener un nombre")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 a 50 caracteres")
    private String name;
}
