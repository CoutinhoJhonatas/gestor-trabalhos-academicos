package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.AuthenticationRequest;
import com.git.gestor_academico.dtos.response.AuthenticationResponse;
import com.git.gestor_academico.security.jwt.JwtUtil;
import com.git.gestor_academico.services.exceptions.ForbiddenException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class.getName());

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            LOG.error("Incorrect username or password");
            throw new ForbiddenException("Usuário ou senha incorreto");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, userService.buscarDadosUsuario(authenticationRequest.getUsername()));
    }

}
