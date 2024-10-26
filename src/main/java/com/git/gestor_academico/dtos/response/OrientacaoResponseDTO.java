package com.git.gestor_academico.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrientacaoResponseDTO {

    private Long id;
    private String status;
    private String motivoRecusado;
    private Long orientadorMatricula;
    private Long tccId;

}
