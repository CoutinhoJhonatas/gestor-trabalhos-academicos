package com.git.gestor_academico.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckpointResponseDTO {

    private Long id;
    private Boolean entregaValidada;
    private String observacoes;
    private Boolean necessitaRevisar;
    private String data;

}
