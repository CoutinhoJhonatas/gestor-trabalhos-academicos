package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.request.CheckpointRequestDTO;
import com.git.gestor_academico.dtos.response.CheckpointResponseDTO;
import com.git.gestor_academico.services.CheckpointService;
import com.git.gestor_academico.swagger.CheckpointControllerSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/checkpoints")
@AllArgsConstructor
public class CheckpointController implements CheckpointControllerSwagger {

    private final CheckpointService checkpointService;

    @PostMapping
    public ResponseEntity<CheckpointResponseDTO> save(@Valid @RequestBody CheckpointRequestDTO checkpoint) {
        return new ResponseEntity<>(checkpointService.salvar(checkpoint), HttpStatus.CREATED);
    }
}
