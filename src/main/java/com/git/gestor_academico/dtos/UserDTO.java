package com.git.gestor_academico.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long userId;
    private String username;
    private String password;
    private List<String> roles;

}
