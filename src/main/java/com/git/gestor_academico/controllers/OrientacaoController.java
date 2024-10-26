package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.OrientadorDto;
import com.git.gestor_academico.dtos.request.OrientacaoRequestDTO;
import com.git.gestor_academico.dtos.response.OrientacaoResponseDTO;
import com.git.gestor_academico.services.OrientacaoService;
import com.git.gestor_academico.swagger.OrientacaoControllerSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/orientacoes")
@AllArgsConstructor
public class OrientacaoController implements OrientacaoControllerSwagger {

    private final OrientacaoService orientacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<OrientacaoResponseDTO> procurarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(orientacaoService.buscarPorMatricula(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrientacaoResponseDTO> save(@Valid @RequestBody OrientacaoRequestDTO orientacao) {
        return new ResponseEntity<>(orientacaoService.salvar(orientacao), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrientacaoResponseDTO> atualizar(@PathVariable Long id,
                                                           @Valid @RequestBody OrientacaoRequestDTO orientacao) {
        return new ResponseEntity<>(orientacaoService.atualizar(id, orientacao), HttpStatus.OK);
    }
}
