package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.UserDTO;
import com.git.gestor_academico.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDto) {
        if (Objects.isNull(userDto)) {
            //throw new InvalidPayloadException("Payload cannot be Null");
            throw new RuntimeException("Payload cannot be Null");
        }
        if(userService.findByUsername(userDto.getUsername())){
            //throw new UserIdAlreadyExistException("Username is already taken");
            throw new RuntimeException("Username is already taken");
        }
        return userService.saveUser(userDto);
    }

}
