package com.git.gestor_academico.dtos;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String nome;
    private String role;

}
