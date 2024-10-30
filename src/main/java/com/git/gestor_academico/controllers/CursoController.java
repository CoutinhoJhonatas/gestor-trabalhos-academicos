package com.git.gestor_academico.controllers;

import com.git.gestor_academico.dtos.request.CoordenadorRequestDTO;
import com.git.gestor_academico.dtos.request.CursoRequestDTO;
import com.git.gestor_academico.dtos.response.CursoResponseDTO;
import com.git.gestor_academico.services.CursoService;
import com.git.gestor_academico.swagger.CursoControllerSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/cursos")
@AllArgsConstructor
public class CursoController implements CursoControllerSwagger {

    private final CursoService cursoService;

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> buscarTodos() {
        return new ResponseEntity<>(cursoService.listarTodos(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_ORIENTADOR', 'ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> procurarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(cursoService.buscarPorMatricula(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CursoResponseDTO> save(@Valid @RequestBody CursoRequestDTO curso) {
        return new ResponseEntity<>(cursoService.salvar(curso), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable Long id,
                                                      @Valid @RequestBody CursoRequestDTO curso) {
        return new ResponseEntity<>(cursoService.atualizar(id, curso), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')")
    @PutMapping("/desativar-curso/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        cursoService.desativarCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
