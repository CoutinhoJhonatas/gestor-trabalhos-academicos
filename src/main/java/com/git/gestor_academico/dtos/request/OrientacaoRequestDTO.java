package com.git.gestor_academico.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrientacaoRequestDTO {

    @NotBlank(message = "Campo requerido")
    private String status;

    private String motivoRecusado;

    @NotNull(message = "Campo requerido")
    private Long tccId;

    @NotNull(message = "Campo requerido")
    private Long orientadorMatricula;

}
