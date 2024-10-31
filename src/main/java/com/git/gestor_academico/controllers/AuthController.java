package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.request.AuthenticationRequest;
import com.git.gestor_academico.dtos.response.AuthenticationResponse;
import com.git.gestor_academico.services.AuthService;
import com.git.gestor_academico.swagger.AuthControllerSwagger;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController implements AuthControllerSwagger {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.authentication(authenticationRequest));
    }

}
