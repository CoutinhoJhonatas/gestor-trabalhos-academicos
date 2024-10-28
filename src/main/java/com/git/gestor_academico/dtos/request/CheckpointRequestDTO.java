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
public class CheckpointRequestDTO {

    @NotNull(message = "Campo requerido")
    private Boolean entregaValidada;

    @NotBlank(message = "Campo requerido")
    private String observacoes;

    @NotNull(message = "Campo requerido")
    private Boolean necessitaRevisar;

    @NotNull(message = "Campo requerido")
    private Long orientacaoId;

}
