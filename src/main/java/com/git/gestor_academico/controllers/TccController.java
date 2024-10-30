package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.request.TccRequestDTO;
import com.git.gestor_academico.dtos.response.TccResponseDTO;
import com.git.gestor_academico.services.TccService;
import com.git.gestor_academico.swagger.TccControllerSwagger;
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
@RequestMapping("v1/tccs")
@AllArgsConstructor
public class TccController implements TccControllerSwagger {

    private final TccService tccService;

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<TccResponseDTO>> getAll() {
        return ResponseEntity.ok(tccService.listarTodos());
    }

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TccResponseDTO> procurarPorRegistro(@PathVariable Long id) {
        return new ResponseEntity<>(tccService.buscarPorId(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TccResponseDTO> save(@Valid @RequestBody TccRequestDTO tcc) {
        return new ResponseEntity<>(tccService.salvar(tcc), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TccResponseDTO> atualizar(@PathVariable Long id,
                                                    @Valid @RequestBody TccRequestDTO tcc) {
        return new ResponseEntity<>(tccService.atualizar(id, tcc), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tccService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
