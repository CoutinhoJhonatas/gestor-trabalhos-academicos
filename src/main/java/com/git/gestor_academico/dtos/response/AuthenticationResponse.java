package com.git.gestor_academico.dtos.response;

import com.git.gestor_academico.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private final String jwt;
    private UserDTO usuarioLogado;

}
