package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.response.OrientadorResponseDTO;
import com.git.gestor_academico.dtos.request.OrientadorRequestDTO;
import com.git.gestor_academico.services.OrientadorService;
import com.git.gestor_academico.swagger.OrientadorControllerSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("v1/orientadores")
@AllArgsConstructor
public class OrientadorController implements OrientadorControllerSwagger {

    private final OrientadorService orientadorService;

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrientadorResponseDTO>> getAll() {
        return ResponseEntity.ok(orientadorService.listarTodos());
    }

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping("/{matricula}")
    public ResponseEntity<OrientadorResponseDTO> procurarPorMatricula(@PathVariable Long matricula) {
        return new ResponseEntity<>(orientadorService.buscarPorMatricula(matricula), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<OrientadorResponseDTO> save(@Valid @RequestBody OrientadorRequestDTO orientador) {
        return new ResponseEntity<>(orientadorService.salvar(orientador), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PutMapping("/{matricula}")
    public ResponseEntity<OrientadorResponseDTO> atualizar(@PathVariable Long matricula,
                                                           @Valid @RequestBody OrientadorRequestDTO orientador) {
        return new ResponseEntity<>(orientadorService.atualizar(matricula, orientador), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable Long matricula) {
        orientadorService.deletar(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
